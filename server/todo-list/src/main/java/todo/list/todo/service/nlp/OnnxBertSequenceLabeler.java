package todo.list.todo.service.nlp;

import ai.onnxruntime.OrtException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OnnxBertSequenceLabeler {
    // Unified path: use resources/onnx as single source of truth
    private static final Path RESOURCE_ONNX_DIR = Path.of(System.getProperty("user.dir"))
            .resolve("src")
            .resolve("main")
            .resolve("resources")
            .resolve("onnx")
            .normalize();

    private static final Path MODEL_PATH = RESOURCE_ONNX_DIR.resolve("ner_classifier.onnx");
    private static final Path VOCAB_PATH = RESOURCE_ONNX_DIR.resolve("vocab.txt");

    private final OnnxBertModel model;
    private final List<String> labelNames;
    private final boolean enabled;

    public OnnxBertSequenceLabeler() {
        Path modelPath = resolvePath("ONNX_NER_MODEL_PATH", MODEL_PATH);
        Path vocabPath = resolvePath("ONNX_VOCAB_PATH", VOCAB_PATH);
        this.model = new OnnxBertModel(modelPath, vocabPath, 64);
        this.enabled = model.isEnabled();
        this.labelNames = List.of(
                "O",
                "B-TITLE", "I-TITLE",
                "B-TIME", "I-TIME",
                "B-DATE", "I-DATE",
                "B-PRIORITY", "I-PRIORITY");
    }

    private Path resolvePath(String envName, Path defaultPath) {
        String envValue = System.getenv(envName);
        if (envValue != null && !envValue.isBlank())
            return Path.of(envValue);

        return defaultPath;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Map<String, String> predictEntities(String text) {
        if (!enabled)
            return Map.of();

        try {
            OnnxBertModel.InferenceResult result = model.infer(text);
            List<String> tokens = result.getTokens();
            float[][] logits = result.getLogits();
            List<String> labels = decodeLabels(logits);
            System.out.println("[ONNX] NER tokens: " + tokens);
            System.out.println("[ONNX] NER labels: " + labels);
            return extractEntities(tokens, labels);
        } catch (OrtException | IOException e) {
            System.out.println("[ONNX] NER prediction failed: " + e.getMessage());
        }

        return Map.of();
    }

    private List<String> decodeLabels(float[][] logits) {
        List<String> labels = new ArrayList<>();
        for (float[] tokenLogits : logits) {
            int best = 0;
            float bestScore = tokenLogits[0];
            for (int j = 1; j < tokenLogits.length; j++) {
                if (tokenLogits[j] > bestScore) {
                    bestScore = tokenLogits[j];
                    best = j;
                }
            }

            if (best >= 0 && best < labelNames.size()) {
                labels.add(labelNames.get(best));
            } else {
                labels.add("O");
            }
        }

        return labels;
    }

    private Map<String, String> extractEntities(List<String> tokens, List<String> labels) {
        Map<String, String> entities = new LinkedHashMap<>();
        String currentType = null;
        StringBuilder currentValue = new StringBuilder();

        for (int i = 0; i < tokens.size() && i < labels.size(); i++) {
            String token = tokens.get(i);
            String label = labels.get(i);

            if ("[CLS]".equals(token) || "[SEP]".equals(token))
                continue;

            if (label.equals("O")) {
                flushEntity(entities, currentType, currentValue);
                currentType = null;
                continue;
            }

            String[] parts = label.split("-");
            if (parts.length != 2) {
                flushEntity(entities, currentType, currentValue);
                currentType = null;
                continue;
            }

            String prefix = parts[0];
            String type = parts[1];
            if (prefix.equals("B") || !type.equals(currentType)) {
                flushEntity(entities, currentType, currentValue);
                currentType = type;
                currentValue.setLength(0);
            }

            String normalizedToken = normalizeToken(token);
            if (normalizedToken.isEmpty())
                continue;

            if (isContinuationToken(token) && currentValue.length() > 0) {
                currentValue.append(normalizedToken);
            } else {
                if (currentValue.length() > 0)
                    currentValue.append(" ");

                currentValue.append(normalizedToken);
            }
        }

        flushEntity(entities, currentType, currentValue);
        return entities;
    }

    private boolean isContinuationToken(String token) {
        if (token.startsWith("##"))
            return true;

        if (!token.startsWith("_") && !token.startsWith("▁") && token.endsWith("@@"))
            return true;

        return false;
    }

    private String normalizeToken(String token) {
        if (token.startsWith("##"))
            return token.substring(2);

        if (token.startsWith("_") || token.startsWith("▁"))
            token = token.substring(1);

        if (token.endsWith("@@"))
            token = token.substring(0, token.length() - 2);

        return token;
    }

    private void flushEntity(Map<String, String> entities, String currentType, StringBuilder currentValue) {
        if (currentType != null && currentValue.length() > 0) {
            entities.put(currentType, currentValue.toString().trim());
            currentValue.setLength(0);
        }
    }
}
