import argparse
import subprocess
import sys
from pathlib import Path


def run_command(command, cwd=None):
    result = subprocess.run(command, cwd=cwd, text=True, capture_output=True)
    if result.returncode != 0:
        raise RuntimeError(
            f"Command failed: {' '.join(command)}\n"
            f"stdout:\n{result.stdout}\n"
            f"stderr:\n{result.stderr}"
        )


def main():
    parser = argparse.ArgumentParser(description="Train and export intent and NER ONNX models for the todo-list backend")
    parser.add_argument("--intent-dataset", default="docs/datasets/prompt-dataset.md", help="Prompt dataset file path")
    parser.add_argument("--ner-dataset", default="docs/datasets/ner-dataset.md", help="NER dataset file path")
    parser.add_argument("--output-dir", default="server/todo-list/src/main/resources/onnx", help="Shared output directory for ONNX models and tokenizer files")
    parser.add_argument("--epochs", type=int, default=8, help="Number of training epochs for both models")
    parser.add_argument("--batch-size", type=int, default=8, help="Batch size for both models")
    parser.add_argument("--learning-rate", type=float, default=2e-5, help="Learning rate for both models")
    parser.add_argument("--skip-intent", action="store_true", help="Skip intent model training and export")
    parser.add_argument("--skip-ner", action="store_true", help="Skip NER model training and export")
    args = parser.parse_args()

    project_root = Path(__file__).resolve().parent
    repo_root = project_root.parent
    output_dir = repo_root / args.output_dir
    output_dir.mkdir(parents=True, exist_ok=True)

    python_executable = sys.executable
    intent_dataset_path = (repo_root / args.intent_dataset).resolve()
    ner_dataset_path = (repo_root / args.ner_dataset).resolve()

    if not args.skip_intent:
        print("\n=== Fine-tuning intent classifier ===\n")
        run_command(
            [
                python_executable,
                str(project_root / "fine_tune_intent_classifier.py"),
                "--dataset",
                str(intent_dataset_path),
                "--output-dir",
                str(output_dir),
                "--epochs",
                str(args.epochs),
                "--batch-size",
                str(args.batch_size),
                "--learning-rate",
                str(args.learning_rate),
            ],
            cwd=project_root,
        )

        print("\n=== Exporting intent ONNX model ===\n")
        run_command(
            [
                python_executable,
                str(project_root / "export_phobert_to_onnx.py"),
                "--model-name",
                str(output_dir),
                "--tokenizer-name",
                str(output_dir),
                "--output-dir",
                str(output_dir),
            ],
            cwd=project_root,
        )

    if not args.skip_ner:
        print("\n=== Fine-tuning NER classifier ===\n")
        run_command(
            [
                python_executable,
                str(project_root / "fine_tune_ner_classifier.py"),
                "--dataset",
                str(ner_dataset_path),
                "--output-dir",
                str(output_dir),
                "--epochs",
                str(args.epochs),
                "--batch-size",
                str(args.batch_size),
                "--learning-rate",
                str(args.learning_rate),
            ],
            cwd=project_root,
        )

        print("\n=== Exporting NER ONNX model ===\n")
        run_command(
            [
                python_executable,
                str(project_root / "export_phobert_ner_to_onnx.py"),
                "--model-name",
                str(output_dir),
                "--tokenizer-name",
                str(output_dir),
                "--output-dir",
                str(output_dir),
            ],
            cwd=project_root,
        )

    print("\n=== Automation complete ===")
    print(f"ONNX models and tokenizer files are available in: {output_dir}")


if __name__ == "__main__":
    main()
