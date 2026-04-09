from pathlib import Path
import re

workspace_root = Path(r"d:\Thanhs_Documents\Work_Space\PROJECTS\todo-list")
prompt_path = workspace_root / "docs" / "datasets" / "prompt-dataset.md"
ner_path = workspace_root / "docs" / "datasets" / "ner-dataset.md"


def simple_tokenize(text):
    return re.findall(r"[\wÀ-ỹ]+|[.,!?;:()\[\]\\/]|\S", text, flags=re.UNICODE)


def align_subsequence(tokens, sub_tokens):
    n, m = len(tokens), len(sub_tokens)
    if m == 0:
        return None
    for i in range(n - m + 1):
        if tokens[i:i + m] == sub_tokens:
            return i, i + m
    return None


def build_labels(prompt, title, due_date, priority):
    tokens = simple_tokenize(prompt)
    labels = ["O"] * len(tokens)

    def annotate(span, label):
        if span is None:
            return
        start, end = span
        labels[start] = f"B-{label}"
        for j in range(start + 1, end):
            labels[j] = f"I-{label}"

    if title and title.lower() not in {"none", "nhiệm vụ ai", "task cơ bản"}:
        title_tokens = simple_tokenize(title)
        span = align_subsequence([t.lower() for t in tokens], [t.lower() for t in title_tokens])
        annotate(span, "TITLE")

    if due_date and due_date.lower() not in {"none", "n/a", "không rõ", ""}:
        date_tokens = simple_tokenize(due_date)
        span = align_subsequence([t.lower() for t in tokens], [t.lower() for t in date_tokens])
        annotate(span, "DATE")

    # annotate time from prompt text heuristically
    for regex in [r"\d{1,2}h(?:[:\.]\d{1,2})?", r"\d{1,2}:\d{2}", r"sáng|chiều|tối|trưa|đêm"]:
        for m in re.finditer(regex, prompt, flags=re.IGNORECASE):
            phrase = m.group(0)
            tokens_span = simple_tokenize(phrase)
            span = align_subsequence([t.lower() for t in tokens], [t.lower() for t in tokens_span])
            annotate(span, "TIME")

    if priority and priority.lower() not in {"none", "n/a"}:
        base = priority.lower()
        candidates = [base]
        if base == "high":
            candidates.append("cao")
        elif base == "medium":
            candidates.append("trung bình")
        elif base == "low":
            candidates.append("thấp")
        for c in candidates:
            tokens_c = simple_tokenize(c)
            span = align_subsequence([t.lower() for t in tokens], [t.lower() for t in tokens_c])
            if span:
                annotate(span, "PRIORITY")
                break

    return tokens, labels


records = []
entry = None
with prompt_path.open("r", encoding="utf-8") as f:
    for line in f:
        line = line.rstrip("\n")
        if line.startswith("- prompt:"):
            if entry:
                records.append(entry)
            entry = {"prompt": line.split("- prompt:", 1)[1].strip().strip('`'),
                     "intent": None,
                     "expected_title": None,
                     "expected_priority": None,
                     "expected_status": None,
                     "expected_dueDate": None,
                     "expected_notes": None}
        elif entry is not None:
            if line.startswith("- intent:"):
                entry["intent"] = line.split("- intent:", 1)[1].strip().strip('`')
            elif line.startswith("- expected title:"):
                entry["expected_title"] = line.split("- expected title:", 1)[1].strip().strip('`')
            elif line.startswith("- expected priority:"):
                entry["expected_priority"] = line.split("- expected priority:", 1)[1].strip().strip('`')
            elif line.startswith("- expected status:"):
                entry["expected_status"] = line.split("- expected status:", 1)[1].strip().strip('`')
            elif line.startswith("- expected dueDate:"):
                entry["expected_dueDate"] = line.split("- expected dueDate:", 1)[1].strip().strip('`')
            elif line.startswith("- expected notes:"):
                entry["expected_notes"] = line.split("- expected notes:", 1)[1].strip().strip('`')
    if entry:
        records.append(entry)

print(f"Loaded {len(records)} records from prompt dataset")

seen_prompts = set()
generated = []
for rec in records:
    prompt = rec["prompt"]
    if prompt in seen_prompts:
        continue
    seen_prompts.add(prompt)
    tokens, labels = build_labels(prompt, rec["expected_title"], rec["expected_dueDate"], rec["expected_priority"])
    generated.append({"sentence": prompt, "labels": labels})

print(f"Generated {len(generated)} NER examples")

with ner_path.open("w", encoding="utf-8") as f:
    f.write("# NER dataset generated from prompt dataset\n\n")
    for ex in generated:
        f.write(f"- sentence: `{ex['sentence']}`\n")
        f.write(f"- labels: {' '.join(ex['labels'])}\n\n")

print(f"Updated NER dataset file: {ner_path}")
