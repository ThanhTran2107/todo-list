import argparse
import tempfile
from pathlib import Path

from transformers import AutoTokenizer, AutoModelForSequenceClassification
from optimum.onnxruntime import ORTModelForSequenceClassification


def main():
    parser = argparse.ArgumentParser(description="Export a PhoBERT/BERT intent classifier to ONNX")
    parser.add_argument("--model-name", default="vinai/phobert-base", help="Hugging Face model name or local model directory for the fine-tuned model")
    parser.add_argument("--tokenizer-name", default="vinai/phobert-base", help="Hugging Face model name or local tokenizer directory to use for tokenization")
    parser.add_argument("--num-labels", type=int, default=4, help="Số nhãn intent")
    parser.add_argument("--output-dir", default="server/todo-list/src/main/resources/onnx", help="Thư mục lưu ONNX và vocab")
    parser.add_argument("--device", default="cpu", help="Device to use for model loading")
    args = parser.parse_args()

    output_dir = Path(args.output_dir)
    output_dir.mkdir(parents=True, exist_ok=True)

    print(f"Loading tokenizer from {args.tokenizer_name}")
    tokenizer = AutoTokenizer.from_pretrained(args.tokenizer_name)
    print(f"Saving tokenizer files to {output_dir}")
    tokenizer.save_pretrained(output_dir)

    print(f"Loading sequence classification model from {args.model_name}")
    model = AutoModelForSequenceClassification.from_pretrained(args.model_name, num_labels=args.num_labels)

    with tempfile.TemporaryDirectory() as temp_dir:
        temp_path = Path(temp_dir)
        print(f"Saving temporary fine-tuned model to {temp_path}")
        model.save_pretrained(temp_path)

        print("Exporting ONNX model from temporary directory...")
        ort_model = ORTModelForSequenceClassification.from_pretrained(temp_path)
        ort_model.save_pretrained(output_dir)

    onnx_model_path = output_dir / "model.onnx"
    expected_path = output_dir / "intent_classifier.onnx"
    if onnx_model_path.exists():
        if expected_path.exists():
            expected_path.unlink()
        onnx_model_path.rename(expected_path)
        print(f"Renamed ONNX model to {expected_path}")
    else:
        print(f"Warning: expected ONNX file not found at {onnx_model_path}")

    print("Done.")
    print(f"ONNX model saved to {expected_path}")
    print(f"Tokenizer files saved to {output_dir}")


if __name__ == "__main__":
    main()
