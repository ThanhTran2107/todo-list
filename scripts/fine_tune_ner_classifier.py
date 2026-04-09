import argparse
from pathlib import Path

import torch
from transformers import AutoTokenizer, AutoModelForTokenClassification, TrainingArguments, Trainer


class NerDataset(torch.utils.data.Dataset):
    def __init__(self, encodings, labels):
        self.encodings = encodings
        self.labels = labels

    def __len__(self):
        return len(self.labels)

    def __getitem__(self, idx):
        item = {k: torch.tensor(v[idx]) for k, v in self.encodings.items()}
        item["labels"] = torch.tensor(self.labels[idx])
        return item


def extract_value(line: str):
    colon = line.find(":")
    if colon < 0:
        return line.strip()
    return line[colon + 1 :].strip().strip("`\"")


def load_ner_dataset(dataset_path: Path):
    if not dataset_path.exists():
        raise FileNotFoundError(f"NER dataset file not found: {dataset_path}")

    sentences = []
    labels = []
    sentence = None
    label_line = None

    with dataset_path.open("r", encoding="utf-8") as f:
        for raw_line in f:
            line = raw_line.strip()
            if line.startswith("- sentence:"):
                if sentence is not None and label_line is not None:
                    sentences.append(sentence)
                    labels.append(label_line.split())
                sentence = extract_value(line)
                label_line = None
                continue

            if line.startswith("- labels:"):
                label_line = extract_value(line)
                continue

            if line == "" and sentence is not None and label_line is not None:
                sentences.append(sentence)
                labels.append(label_line.split())
                sentence = None
                label_line = None

    if sentence is not None and label_line is not None:
        sentences.append(sentence)
        labels.append(label_line.split())

    return sentences, labels


def align_labels(sentences, labels, tokenizer, label2id, max_length):
    if getattr(tokenizer, "is_fast", False):
        encodings = tokenizer(
            [sentence.split() for sentence in sentences],
            is_split_into_words=True,
            truncation=True,
            padding="max_length",
            max_length=max_length,
            return_tensors="pt",
        )

        aligned_labels = []
        for i in range(len(sentences)):
            word_ids = encodings.word_ids(batch_index=i)
            sentence_labels = []
            previous_word_idx = None
            word_labels = labels[i]
            for word_idx in word_ids:
                if word_idx is None:
                    sentence_labels.append(-100)
                elif word_idx != previous_word_idx:
                    sentence_labels.append(label2id.get(word_labels[word_idx], label2id["O"]))
                    previous_word_idx = word_idx
                else:
                    current_label = word_labels[word_idx]
                    if current_label == "O":
                        sentence_labels.append(label2id["O"])
                    elif current_label.startswith("B-"):
                        sentence_labels.append(label2id.get(current_label.replace("B-", "I-"), label2id["O"]))
                    else:
                        sentence_labels.append(label2id.get(current_label, label2id["O"]))
            aligned_labels.append(sentence_labels)

        return encodings, aligned_labels

    encodings = tokenizer(
        sentences,
        truncation=True,
        padding="max_length",
        max_length=max_length,
        return_tensors="pt",
    )

    aligned_labels = []
    for i, sentence in enumerate(sentences):
        word_labels = labels[i]
        word_tokens = []
        word_ids = []
        for word_idx, word in enumerate(sentence.split()):
            subwords = tokenizer.tokenize(word)
            if not subwords:
                subwords = [tokenizer.unk_token or "[UNK]"]
            word_tokens.extend(subwords)
            word_ids.extend([word_idx] * len(subwords))

        token_word_ids = [None] + word_ids + [None]
        sentence_label_ids = []
        previous_word_idx = None
        for word_idx in token_word_ids:
            if word_idx is None:
                sentence_label_ids.append(-100)
            elif word_idx >= len(word_labels):
                sentence_label_ids.append(label2id["O"])
                previous_word_idx = word_idx
            elif word_idx != previous_word_idx:
                sentence_label_ids.append(label2id.get(word_labels[word_idx], label2id["O"]))
                previous_word_idx = word_idx
            else:
                current_label = word_labels[word_idx]
                if current_label == "O":
                    sentence_label_ids.append(label2id["O"])
                elif current_label.startswith("B-"):
                    sentence_label_ids.append(label2id.get(current_label.replace("B-", "I-"), label2id["O"]))
                else:
                    sentence_label_ids.append(label2id.get(current_label, label2id["O"]))

        if len(sentence_label_ids) > max_length:
            sentence_label_ids = sentence_label_ids[:max_length]
        else:
            sentence_label_ids.extend([-100] * (max_length - len(sentence_label_ids)))

        aligned_labels.append(sentence_label_ids)

    return encodings, aligned_labels


def main():
    parser = argparse.ArgumentParser(description="Fine-tune PhoBERT NER token classifier on project dataset")
    parser.add_argument("--model-name", default="vinai/phobert-base", help="Hugging Face model name or local model path")
    parser.add_argument("--tokenizer-name", default="vinai/phobert-base", help="Tokenizer name or local tokenizer path")
    parser.add_argument("--dataset", default="docs/datasets/ner-dataset.md", help="Path to NER dataset file")
    parser.add_argument("--output-dir", default="server/todo-list/src/main/resources/onnx", help="Output directory for fine-tuned model")
    parser.add_argument("--epochs", type=int, default=8, help="Number of training epochs")
    parser.add_argument("--batch-size", type=int, default=8, help="Training batch size")
    parser.add_argument("--max-length", type=int, default=128, help="Maximum token length")
    parser.add_argument("--learning-rate", type=float, default=2e-5, help="Learning rate")
    args = parser.parse_args()

    dataset_path = Path(args.dataset)
    output_dir = Path(args.output_dir)
    output_dir.mkdir(parents=True, exist_ok=True)

    print(f"Loading NER dataset from {dataset_path}")
    sentences, labels = load_ner_dataset(dataset_path)
    if not sentences:
        raise RuntimeError(f"No NER samples loaded from {dataset_path}")

    label_list = ["O", "B-TITLE", "I-TITLE", "B-TIME", "I-TIME", "B-DATE", "I-DATE", "B-PRIORITY", "I-PRIORITY"]
    label2id = {label: idx for idx, label in enumerate(label_list)}
    id2label = {idx: label for label, idx in label2id.items()}

    tokenizer = AutoTokenizer.from_pretrained(args.tokenizer_name, use_fast=True)
    print(f"Tokenizing {len(sentences)} NER sentences")
    encodings, aligned = align_labels(sentences, labels, tokenizer, label2id, args.max_length)

    train_dataset = NerDataset(encodings, aligned)

    model = AutoModelForTokenClassification.from_pretrained(
        args.model_name,
        num_labels=len(label_list),
        id2label=id2label,
        label2id=label2id,
    )

    training_args = TrainingArguments(
        output_dir=str(output_dir / "ner_classifier_finetuned"),
        num_train_epochs=args.epochs,
        per_device_train_batch_size=args.batch_size,
        learning_rate=args.learning_rate,
        logging_steps=10,
        save_strategy="epoch",
        seed=42,
        fp16=False,
        push_to_hub=False,
    )

    trainer = Trainer(
        model=model,
        args=training_args,
        train_dataset=train_dataset,
        tokenizer=tokenizer,
    )

    print("Starting NER fine-tuning...")
    trainer.train()

    print(f"Saving fine-tuned NER model to {output_dir}")
    model.save_pretrained(output_dir)
    tokenizer.save_pretrained(output_dir)

    print("NER fine-tuning completed.")
    print("Next step: run scripts/export_phobert_ner_to_onnx.py --model-name <output-dir> --tokenizer-name vinai/phobert-base")


if __name__ == "__main__":
    main()
