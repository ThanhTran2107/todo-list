import argparse
import tempfile
from pathlib import Path

from transformers import AutoTokenizer, AutoModelForTokenClassification
from optimum.onnxruntime import ORTModelForTokenClassification


def parse_labels(labels_value: str):
    return [label.strip() for label in labels_value.split(",") if label.strip()]


def main():
    parser = argparse.ArgumentParser(description="Export a PhoBERT-based NER token classification model to ONNX")
    parser.add_argument(
        "--model-name",
        default="vinai/phobert-base",
        help="Hugging Face model name or local model folder for the NER model",
    )
    parser.add_argument(
        "--tokenizer-name",
        default="vinai/phobert-base",
        help="Hugging Face tokenizer name or local tokenizer folder",
    )
    parser.add_argument(
        "--labels",
        default="O,B-TITLE,I-TITLE,B-TIME,I-TIME,B-DATE,I-DATE,B-PRIORITY,I-PRIORITY",
        help="Comma-separated list of NER labels in the token classification head",
    )
    parser.add_argument(
        "--output-dir",
        default="server/todo-list/src/main/resources/onnx",
        help="Directory where the ONNX model and tokenizer files will be saved",
    )
    args = parser.parse_args()

    output_dir = Path(args.output_dir)
    output_dir.mkdir(parents=True, exist_ok=True)

    labels = parse_labels(args.labels)
    id2label = {idx: label for idx, label in enumerate(labels)}
    label2id = {label: idx for idx, label in enumerate(labels)}

    print(f"Loading tokenizer from {args.tokenizer_name}")
    tokenizer = AutoTokenizer.from_pretrained(args.tokenizer_name)
    print(f"Saving tokenizer files to {output_dir}")
    tokenizer.save_pretrained(output_dir)

    print(f"Loading token classification model from {args.model_name}")
    model = AutoModelForTokenClassification.from_pretrained(
        args.model_name,
        num_labels=len(labels),
        id2label=id2label,
        label2id=label2id,
    )

    with tempfile.TemporaryDirectory() as temp_dir:
        temp_path = Path(temp_dir)
        print(f"Saving temporary NER model to {temp_path}")
        model.save_pretrained(temp_path)

        print("Exporting ONNX NER model from temporary directory...")
        ort_model = ORTModelForTokenClassification.from_pretrained(temp_path)
        ort_model.save_pretrained(output_dir)

    onnx_model_path = output_dir / "model.onnx"
    expected_path = output_dir / "ner_classifier.onnx"
    if onnx_model_path.exists():
        if expected_path.exists():
            expected_path.unlink()
        onnx_model_path.rename(expected_path)
        print(f"Renamed ONNX model to {expected_path}")
    else:
        print(f"Warning: expected ONNX file not found at {onnx_model_path}")

    print("Done.")
    print(f"NER ONNX model saved to {expected_path}")
    print(f"Tokenizer files saved to {output_dir}")


if __name__ == "__main__":
    main()
