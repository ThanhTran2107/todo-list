package todo.list.todo.service.nlp;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import ai.onnxruntime.OrtSession.Result;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OnnxBertIntentClassifier {
    // Unified path: use resources/onnx as single source of truth
    private static final Path RESOURCE_ONNX_DIR = Path.of(System.getProperty("user.dir"))
            .resolve("src")
            .resolve("main")
            .resolve("resources")
            .resolve("onnx")
            .normalize();

    private static final Path MODEL_PATH = RESOURCE_ONNX_DIR.resolve("intent_classifier.onnx");
    private static final Path VOCAB_PATH = RESOURCE_ONNX_DIR.resolve("vocab.txt");

    private final OrtEnvironment environment;
    private final OrtSession session;
    private final OnnxBertTokenizer tokenizer;
    private final List<String> labels;
    private final boolean enabled;
    private final int maxSequenceLength = 64;

    public OnnxBertIntentClassifier() {
        Path modelPath = resolvePath("ONNX_INTENT_MODEL_PATH", MODEL_PATH);
        Path vocabPath = resolvePath("ONNX_VOCAB_PATH", VOCAB_PATH);
        boolean loaded = false;
        OrtEnvironment env = null;
        OrtSession sess = null;
        OnnxBertTokenizer tokenizerInstance = null;
        try {
            if (Files.exists(modelPath) && Files.exists(vocabPath)) {
                env = OrtEnvironment.getEnvironment();
                sess = env.createSession(modelPath.toString(), new OrtSession.SessionOptions());
                tokenizerInstance = new OnnxBertTokenizer(vocabPath, maxSequenceLength);
                loaded = true;
            } else {
                System.out.println("[ONNX] Model or vocab not found, ONNX BERT disabled.");
            }
        } catch (OrtException | IOException e) {
            System.out.println("[ONNX] Failed to initialize ONNX BERT classifier: " + e.getMessage());
            loaded = false;
            if (sess != null) {
                try {
                    sess.close();
                } catch (OrtException ignored) {
                }
            }
        }

        this.environment = env;
        this.session = sess;
        this.tokenizer = tokenizerInstance;
        this.labels = List.of("CREATE", "VIEW", "UPDATE", "DELETE");
        this.enabled = loaded;
    }

    private Path resolvePath(String envName, Path defaultPath) {
        String envValue = System.getenv(envName);
        if (envValue != null && !envValue.isBlank()) {
            return Path.of(envValue);
        }
        return defaultPath;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String predict(String text) {
        if (!enabled || text == null)
            return "CREATE";

        try {
            List<String> tokens = tokenizer.tokenize(text);
            long[] inputIds = tokenizer.convertTokensToIds(tokens);
            long[] attentionMask = tokenizer.buildAttentionMask(tokens);
            long[] tokenTypeIds = tokenizer.buildTokenTypeIds(tokens);

            long[][] inputIdsBatch = new long[1][maxSequenceLength];
            long[][] attentionMaskBatch = new long[1][maxSequenceLength];
            long[][] tokenTypeIdsBatch = new long[1][maxSequenceLength];
            inputIdsBatch[0] = inputIds;
            attentionMaskBatch[0] = attentionMask;
            tokenTypeIdsBatch[0] = tokenTypeIds;

            Map<String, OnnxTensor> inputs = new HashMap<>();
            List<OnnxTensor> tensorsToClose = new ArrayList<>();
            try {
                Set<String> sessionInputs = session.getInputInfo().keySet();

                if (sessionInputs.contains("input_ids")) {
                    OnnxTensor inputIdsTensor = OnnxTensor.createTensor(environment, inputIdsBatch);
                    inputs.put("input_ids", inputIdsTensor);
                    tensorsToClose.add(inputIdsTensor);
                }
                if (sessionInputs.contains("attention_mask")) {
                    OnnxTensor attentionTensor = OnnxTensor.createTensor(environment, attentionMaskBatch);
                    inputs.put("attention_mask", attentionTensor);
                    tensorsToClose.add(attentionTensor);
                }
                if (sessionInputs.contains("token_type_ids")) {
                    OnnxTensor tokenTypeTensor = OnnxTensor.createTensor(environment, tokenTypeIdsBatch);
                    inputs.put("token_type_ids", tokenTypeTensor);
                    tensorsToClose.add(tokenTypeTensor);
                }

                if (inputs.isEmpty()) {
                    throw new IllegalStateException(
                            "No compatible inputs found for ONNX intent model: " + sessionInputs);
                }

                try (Result result = session.run(inputs)) {
                    float[][] logits = (float[][]) result.get(0).getValue();
                    int bestIndex = argMax(logits[0]);
                    if (bestIndex >= 0 && bestIndex < labels.size()) {
                        return labels.get(bestIndex);
                    }
                }
            } finally {
                for (OnnxTensor tensor : tensorsToClose) {
                    try {
                        tensor.close();
                    } catch (Exception ignored) {
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("[ONNX] Prediction failed: " + e.getMessage());
        }

        return "CREATE";
    }

    private int argMax(float[] logits) {
        int best = 0;
        float bestScore = Float.NEGATIVE_INFINITY;
        for (int i = 0; i < logits.length; i++) {
            if (logits[i] > bestScore) {
                bestScore = logits[i];
                best = i;
            }
        }

        return best;
    }
}
