package todo.list.todo.service.nlp;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TransformerTextClassifier<T> {
    private static final Pattern TOKEN_SPLIT = Pattern.compile("[^\\p{L}\\p{Nd}]+", Pattern.UNICODE_CHARACTER_CLASS);
    private final Map<String, Integer> tokenIndex;
    private final List<T> labels;
    private final double[][] tokenEmbeddings; // Trainable embeddings
    private final double[][] hiddenWeights; // Hidden layer: dModel -> hiddenDim
    private final double[] hiddenBias;
    private final double[][] outputWeights; // Output layer: hiddenDim -> labelCount
    private final double[] outputBias;
    private final T defaultLabel;
    private final int dModel;
    private final int hiddenDim;

    private TransformerTextClassifier(Map<String, Integer> tokenIndex,
            List<T> labels,
            double[][] tokenEmbeddings,
            double[][] hiddenWeights,
            double[] hiddenBias,
            double[][] outputWeights,
            double[] outputBias,
            T defaultLabel,
            int dModel,
            int hiddenDim) {
        this.tokenIndex = tokenIndex;
        this.labels = labels;
        this.tokenEmbeddings = tokenEmbeddings;
        this.hiddenWeights = hiddenWeights;
        this.hiddenBias = hiddenBias;
        this.outputWeights = outputWeights;
        this.outputBias = outputBias;
        this.defaultLabel = defaultLabel;
        this.dModel = dModel;
        this.hiddenDim = hiddenDim;
    }

    public T predict(String text) {
        List<Integer> tokenIds = toTokenIds(normalizeText(text));
        if (tokenIds.isEmpty())
            return defaultLabel;

        double[] pooled = getMeanEmbeddings(tokenIds);
        if (pooled == null)
            return defaultLabel;

        double[] hidden = forwardHidden(pooled);
        double[] logits = forwardOutput(hidden);
        int best = argMax(logits);

        return labels.get(best);
    }

    private double[] getMeanEmbeddings(List<Integer> tokenIds) {
        double[] sum = new double[dModel];
        for (int tokenId : tokenIds) {
            double[] emb = tokenEmbeddings[tokenId];
            for (int j = 0; j < dModel; j++) {
                sum[j] += emb[j];
            }
        }
        for (int j = 0; j < dModel; j++) {
            sum[j] /= tokenIds.size();
        }
        return sum;
    }

    private double[] forwardHidden(double[] input) {
        double[] hidden = new double[hiddenDim];
        for (int i = 0; i < hiddenDim; i++) {
            double sum = 0.0;
            for (int j = 0; j < dModel; j++) {
                sum += hiddenWeights[j][i] * input[j];
            }
            sum += hiddenBias[i];
            hidden[i] = Math.max(0.0, sum); // ReLU
        }
        return hidden;
    }

    private double[] forwardOutput(double[] hidden) {
        double[] logits = new double[labels.size()];
        for (int i = 0; i < labels.size(); i++) {
            double sum = 0.0;
            for (int j = 0; j < hiddenDim; j++) {
                sum += outputWeights[j][i] * hidden[j];
            }
            logits[i] = sum + outputBias[i];
        }
        return logits;
    }

    private int argMax(double[] logits) {
        int best = 0;
        double bestScore = logits[0];
        for (int i = 1; i < logits.length; i++) {
            if (logits[i] > bestScore) {
                bestScore = logits[i];
                best = i;
            }
        }

        return best;
    }

    private List<Integer> toTokenIds(String text) {
        List<String> tokens = tokenize(text);
        List<Integer> ids = new ArrayList<>();
        for (String token : tokens) {
            ids.add(tokenIndex.getOrDefault(token, tokenIndex.getOrDefault("<unk>", 0)));
        }

        return ids;
    }

    private static List<String> tokenize(String text) {
        if (text == null)
            return Collections.emptyList();

        return Arrays.stream(TOKEN_SPLIT.split(text.toLowerCase(Locale.ROOT)))
                .filter(token -> !token.isBlank())
                .collect(Collectors.toList());
    }

    private static String normalizeText(String text) {
        return text == null ? "" : text.trim().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
    }

    public static <T> TransformerTextClassifier<T> train(List<LabeledText<T>> trainingData, T defaultLabel) {
        Set<String> vocabulary = new LinkedHashSet<>();
        LinkedHashSet<T> labelSet = new LinkedHashSet<>();
        for (LabeledText<T> example : trainingData) {
            labelSet.add(example.getLabel());
            vocabulary.addAll(tokenize(normalizeText(example.getText())));
        }

        vocabulary.add("<unk>");
        Map<String, Integer> tokenIndex = new LinkedHashMap<>();
        int index = 0;
        for (String token : vocabulary) {
            tokenIndex.put(token, index++);
        }

        List<T> labels = new ArrayList<>(labelSet);
        int labelCount = labels.size();
        int dModel = 32; // Increased from 16 for better representation
        int hiddenDim = 64; // Hidden layer size

        Random random = new Random(42);
        double[][] tokenEmbeddings = randomMatrix(vocabulary.size(), dModel, 0.1, random);
        double[][] hiddenWeights = randomMatrix(dModel, hiddenDim, 0.1, random);
        double[] hiddenBias = new double[hiddenDim];
        double[][] outputWeights = randomMatrix(hiddenDim, labelCount, 0.1, random);
        double[] outputBias = new double[labelCount];

        TransformerTextClassifier<T> classifier = new TransformerTextClassifier<>(
                tokenIndex, labels, tokenEmbeddings,
                hiddenWeights, hiddenBias, outputWeights, outputBias,
                defaultLabel, dModel, hiddenDim);

        // Train with proper backpropagation
        classifier.trainWithBackprop(trainingData, 200, 0.01);

        return classifier;
    }

    private void trainWithBackprop(List<LabeledText<T>> trainingData, int epochs, double learningRate) {
        Map<T, Integer> labelIndex = new HashMap<>();
        for (int i = 0; i < labels.size(); i++) {
            labelIndex.put(labels.get(i), i);
        }

        for (int epoch = 0; epoch < epochs; epoch++) {
            // Shuffle training data each epoch
            List<LabeledText<T>> shuffled = new ArrayList<>(trainingData);
            Collections.shuffle(shuffled, new Random(epoch));

            double totalLoss = 0.0;
            int correct = 0;

            for (LabeledText<T> example : shuffled) {
                List<Integer> tokenIds = toTokenIds(normalizeText(example.getText()));
                if (tokenIds.isEmpty())
                    continue;

                int target = labelIndex.get(example.getLabel());

                // Forward pass
                double[] pooled = getMeanEmbeddings(tokenIds);
                double[] hidden = forwardHidden(pooled);
                double[] logits = forwardOutput(hidden);
                double[] probs = softmaxCopy(logits);

                // Calculate cross-entropy loss
                totalLoss -= Math.log(Math.max(probs[target], 1e-10));
                if (argMax(logits) == target)
                    correct++;

                // Backward pass: compute gradients
                double[] dLogits = new double[labels.size()];
                for (int i = 0; i < labels.size(); i++) {
                    dLogits[i] = probs[i] - ((i == target) ? 1.0 : 0.0);
                }

                // Update output weights and biases
                for (int i = 0; i < labels.size(); i++) {
                    for (int j = 0; j < hiddenDim; j++) {
                        outputWeights[j][i] -= learningRate * dLogits[i] * hidden[j];
                    }
                    outputBias[i] -= learningRate * dLogits[i];
                }

                // Backprop through hidden layer
                double[] dHidden = new double[hiddenDim];
                for (int j = 0; j < hiddenDim; j++) {
                    for (int i = 0; i < labels.size(); i++) {
                        dHidden[j] += outputWeights[j][i] * dLogits[i];
                    }
                    // ReLU gradient
                    if (hidden[j] <= 0)
                        dHidden[j] = 0;
                }

                // Update hidden weights and biases
                for (int j = 0; j < dModel; j++) {
                    for (int k = 0; k < hiddenDim; k++) {
                        hiddenWeights[j][k] -= learningRate * dHidden[k] * pooled[j];
                    }
                }
                for (int k = 0; k < hiddenDim; k++) {
                    hiddenBias[k] -= learningRate * dHidden[k];
                }

                // Backprop through embeddings
                double[] dPooled = new double[dModel];
                for (int j = 0; j < dModel; j++) {
                    for (int k = 0; k < hiddenDim; k++) {
                        dPooled[j] += hiddenWeights[j][k] * dHidden[k];
                    }
                }

                // Update token embeddings
                double scale = 1.0 / tokenIds.size();
                for (int tokenId : tokenIds) {
                    for (int j = 0; j < dModel; j++) {
                        tokenEmbeddings[tokenId][j] -= learningRate * dPooled[j] * scale;
                    }
                }
            }

            // Print training progress every 50 epochs
            if ((epoch + 1) % 50 == 0) {
                double avgLoss = totalLoss / shuffled.size();
                double accuracy = (double) correct / shuffled.size();
                System.out.printf("[NLP] Epoch %d/%d - Loss: %.4f, Accuracy: %.2f%%%n",
                        epoch + 1, epochs, avgLoss, accuracy * 100);
            }
        }
    }

    private static double[] softmaxCopy(double[] logits) {
        double max = Arrays.stream(logits).max().orElse(0.0);
        double sum = 0.0;
        double[] out = new double[logits.length];
        for (int i = 0; i < logits.length; i++) {
            out[i] = Math.exp(logits[i] - max);
            sum += out[i];
        }

        for (int i = 0; i < out.length; i++) {
            out[i] /= sum;
        }

        return out;
    }

    private static double[][] randomMatrix(int rows, int cols, double scale, Random random) {
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextGaussian() * scale;
            }
        }

        return matrix;
    }

    public static final class LabeledText<T> {
        private final String text;
        private final T label;

        public LabeledText(String text, T label) {
            this.text = text;
            this.label = label;
        }

        public String getText() {
            return text;
        }

        public T getLabel() {
            return label;
        }
    }
}
