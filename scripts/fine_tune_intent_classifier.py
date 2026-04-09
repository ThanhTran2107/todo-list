import argparse
from pathlib import Path
import re

import torch
from transformers import AutoTokenizer, AutoModelForSequenceClassification, TrainingArguments, Trainer


class PromptDataset(torch.utils.data.Dataset):
    def __init__(self, encodings, labels):
        self.encodings = encodings
        self.labels = labels

    def __len__(self):
        return len(self.labels)

    def __getitem__(self, idx):
        item = {k: torch.tensor(v[idx]) for k, v in self.encodings.items()}
        item["labels"] = torch.tensor(self.labels[idx])
        return item


def load_prompt_dataset(dataset_path: Path):
    if not dataset_path.exists():
        raise FileNotFoundError(f"Prompt dataset file not found: {dataset_path}")

    prompts = []
    intents = []
    prompt = None
    intent = None

    with dataset_path.open("r", encoding="utf-8") as f:
        for raw_line in f:
            line = raw_line.strip()
            if line.startswith("- prompt:"):
                if prompt is not None and intent is not None:
                    prompts.append(prompt)
                    intents.append(intent)
                prompt = extract_value(line)
                intent = None
                continue

            if line.startswith("- intent:"):
                intent = extract_value(line)
                continue

            if line == "" and prompt is not None and intent is not None:
                prompts.append(prompt)
                intents.append(intent)
                prompt = None
                intent = None

    if prompt is not None and intent is not None:
        prompts.append(prompt)
        intents.append(intent)

    return prompts, intents


def extract_value(line: str):
    colon = line.find(":")
    if colon < 0:
        return line.strip()
    return line[colon + 1 :].strip().strip("`\"")


def main():
    parser = argparse.ArgumentParser(description="Fine-tune PhoBERT intent classifier on project prompt dataset")
    parser.add_argument("--model-name", default="vinai/phobert-base", help="Hugging Face model name or local model path")
    parser.add_argument("--dataset", default="docs/datasets/prompt-dataset.md", help="Path to prompt dataset file")
    parser.add_argument("--output-dir", default="server/todo-list/src/main/resources/onnx", help="Output directory to save fine-tuned model and tokenizer")
    parser.add_argument("--epochs", type=int, default=8, help="Number of training epochs")
    parser.add_argument("--batch-size", type=int, default=8, help="Training batch size")
    parser.add_argument("--max-length", type=int, default=128, help="Maximum token length for prompt inputs")
    parser.add_argument("--learning-rate", type=float, default=2e-5, help="Learning rate for fine-tuning")
    args = parser.parse_args()

    dataset_path = Path(args.dataset)
    output_dir = Path(args.output_dir)
    output_dir.mkdir(parents=True, exist_ok=True)

    print(f"Loading prompt dataset from {dataset_path}")
    prompts, intents = load_prompt_dataset(dataset_path)
    if not prompts:
        raise RuntimeError(f"No prompt samples loaded from {dataset_path}")

    label_list = ["CREATE", "VIEW", "UPDATE", "DELETE"]
    label2id = {label: idx for idx, label in enumerate(label_list)}
    id2label = {idx: label for label, idx in label2id.items()}

    labels = [label2id.get(intent, label2id["CREATE"]) for intent in intents]

    tokenizer = AutoTokenizer.from_pretrained(args.model_name, use_fast=True)
    print(f"Tokenizing {len(prompts)} prompts")
    encodings = tokenizer(
        prompts,
        truncation=True,
        padding="max_length",
        max_length=args.max_length,
        return_tensors="pt",
    )

    train_dataset = PromptDataset(encodings, labels)

    model = AutoModelForSequenceClassification.from_pretrained(
        args.model_name,
        num_labels=len(label_list),
        id2label=id2label,
        label2id=label2id,
    )

    training_args = TrainingArguments(
        output_dir=str(output_dir / "intent_classifier_finetuned"),
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

    print("Starting fine-tuning...")
    trainer.train()

    print(f"Saving fine-tuned model to {output_dir}")
    model.save_pretrained(output_dir)
    tokenizer.save_pretrained(output_dir)

    print("Fine-tuning completed.")
    print("Next step: run scripts/export_phobert_to_onnx.py --model-name <output-dir> --num-labels 4")


if __name__ == "__main__":
    main()
