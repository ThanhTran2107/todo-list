# ONNX Model & Vocab Setup

## Goal

This guide explains how to generate and place ONNX/BERT model files and vocabulary files for the Java backend.

## Required files

- ONNX intent classification model:
  - `server/todo-list/src/main/resources/onnx/intent_classifier.onnx`
- Matching vocabulary file:
  - `server/todo-list/src/main/resources/onnx/vocab.txt`

## Recommended base model

You can use a pretrained Vietnamese BERT/PhoBERT model such as:

- `vinai/phobert-base`
- `vinai/phobert-large`

For a simpler option, `vinai/phobert-base` is usually sufficient.

### 🚀 Quick Retrain (Single command)

If your Python environment is already set up, run a single command from the repository root:

```bash
cd d:\Thanhs_Documents\Work_Space\PROJECTS\todo-list
python .\scripts\setup_onnx_models.py
```

This command will automatically:

- fine-tune the intent classifier
- fine-tune the NER token classifier
- export both models to ONNX

### Options

- Train only intent and export the intent model:

```bash
python .\scripts\setup_onnx_models.py --skip-ner
```

- Train only NER and export the NER model:

```bash
python .\scripts\setup_onnx_models.py --skip-intent
```

- Change the number of epochs, batch size, or learning rate:

```bash
python .\scripts\setup_onnx_models.py --epochs 8 --batch-size 8 --learning-rate 2e-5
```

- Change the output directory if needed:

```bash
python .\scripts\setup_onnx_models.py --output-dir server/todo-list/src/main/resources/onnx
```

> By default, the script saves output to `server/todo-list/onnx`.

## How to create the ONNX models and vocab

### 1. Install Python dependencies

You can use the shared Python interpreter in your team; creating a `.venv` is optional.

```bash
python -m pip install --upgrade pip
python -m pip install -r scripts/requirements.txt
```

### 2. Run retraining with the main script

Use a single command to train and export both intent and NER:

```bash
python .\scripts\setup_onnx_models.py
```

If you want to run only one part:

```bash
python .\scripts\setup_onnx_models.py --skip-ner
python .\scripts\setup_onnx_models.py --skip-intent
```

> No need to run the long manual commands. The individual fine-tune scripts are only needed if you want more granular control.

### 3. Output location

After completion, files will be placed in:

- `server/todo-list/onnx/intent_classifier.onnx`
- `server/todo-list/onnx/ner_classifier.onnx`
- `server/todo-list/onnx/vocab.txt`
- related tokenizer files

If your backend requires `server/todo-list/src/main/resources/onnx`, copy the files there or use `--output-dir server/todo-list/src/main/resources/onnx`.

> Note: The current PhoBERT NER model may still be weak for phrases like `ngày 20 tháng 4`, `gửi sếp`, `gấp`, `tuần sau`. Add more similar NER examples to `docs/datasets/ner-dataset.md` before training.

### 4. Generate vocab.txt

The vocabulary must match the model. If you use `vinai/phobert-base`, you can export the vocab from Hugging Face:

```python
from transformers import AutoTokenizer

tokenizer = AutoTokenizer.from_pretrained("vinai/phobert-base")
with open("vocab.txt", "w", encoding="utf-8") as f:
    for token, index in sorted(tokenizer.get_vocab().items(), key=lambda x: x[1]):
        f.write(token + "\n")
```

### 5. Manual ONNX export (optional)

Usually you do not need to do anything else after running `python .\scripts\setup_onnx_models.py`.

The manual export scripts are only useful if you want to customize the process:

- `scripts/export_phobert_to_onnx.py`
- `scripts/export_phobert_ner_to_onnx.py`

For example, after fine-tuning a model you could export it with:

```bash
python scripts/export_phobert_to_onnx.py --model-name server/todo-list/onnx --tokenizer-name server/todo-list/onnx --num-labels 4
```

and

```bash
python scripts/export_phobert_ner_to_onnx.py --model-name server/todo-list/onnx --tokenizer-name server/todo-list/onnx
```

But for this guide, the simplest approach is still to run:

```bash
python .\scripts\setup_onnx_models.py
```

### 6. Optional path configuration

```powershell
$env:ONNX_INTENT_MODEL_PATH = "D:\Thanhs_Documents\Work_Space\PROJECTS\todo-list\server\todo-list\src\main\resources\onnx\intent_classifier.onnx"
$env:ONNX_VOCAB_PATH = "D:\Thanhs_Documents\Work_Space\PROJECTS\todo-list\server\todo-list\src\main\resources\onnx\vocab.txt"
```

## Verifying the model

1. Restart the backend or rerun `quarkusDev` / build the project.
2. Send a prompt to the endpoint or parsing function.
3. If the log shows `[NLP] ONNX BERT NLP enabled...`, the ONNX model loaded successfully.
4. If the model did not load, you will see:

```text
[ONNX] Model or vocab not found, ONNX BERT disabled.
```

## Notes

- The vocabulary file must match the ONNX model.
- The ONNX model and `vocab.txt` should come from the same source/model.
- If you use a custom model, you must fine-tune the intent classification layer before exporting to ONNX.
