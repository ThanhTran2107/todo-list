package todo.list.todo.service.nlp;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OnnxBertModel {
    private final OrtEnvironment environment;
    private final OrtSession session;
    private final OnnxBertTokenizer tokenizer;
    private final boolean enabled;
    private final int maxSequenceLength;

    public OnnxBertModel(Path modelPath, Path vocabPath, int maxSequenceLength) {
        this.maxSequenceLength = maxSequenceLength;
        OrtEnvironment env = null;
        OrtSession sess = null;
        OnnxBertTokenizer tokenizerInstance = null;
        boolean loaded = false;

        try {
            if (Files.exists(modelPath) && Files.exists(vocabPath)) {
                env = OrtEnvironment.getEnvironment();
                sess = env.createSession(modelPath.toString(), new OrtSession.SessionOptions());
                tokenizerInstance = new OnnxBertTokenizer(vocabPath, maxSequenceLength);
                loaded = true;
            } else {
                System.out.println("[ONNX] Model or vocab not found: " + modelPath + ", " + vocabPath);
            }
        } catch (OrtException | IOException e) {
            System.out.println("[ONNX] Failed to initialize ONNX model: " + e.getMessage());
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
        this.enabled = loaded;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public InferenceResult infer(String text) throws OrtException, IOException {
        if (!enabled)
            return null;

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
                throw new IllegalStateException("No compatible inputs found for ONNX model: " + sessionInputs);
            }

            try (OrtSession.Result result = session.run(inputs)) {
                Object value = result.get(0).getValue();
                float[][] logits;
                if (value instanceof float[][][]) {
                    float[][][] logits3d = (float[][][]) value;
                    logits = flattenSequenceOutput(logits3d);
                } else if (value instanceof float[][]) {
                    logits = (float[][]) value;
                } else {
                    throw new IllegalStateException("Unexpected ONNX output shape: " + value.getClass());
                }
                return new InferenceResult(tokens, logits);
            }
        } finally {
            for (OnnxTensor tensor : tensorsToClose) {
                try {
                    tensor.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    private float[][] flattenSequenceOutput(float[][][] logits3d) {
        if (logits3d.length == 0)
            return new float[0][];

        return logits3d[0];
    }

    public static final class InferenceResult {
        private final List<String> tokens;
        private final float[][] logits;

        public InferenceResult(List<String> tokens, float[][] logits) {
            this.tokens = tokens;
            this.logits = logits;
        }

        public List<String> getTokens() {
            return tokens;
        }

        public float[][] getLogits() {
            return logits;
        }
    }
}
