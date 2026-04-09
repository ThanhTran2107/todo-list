package todo.list.todo.service.nlp;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TransformerTextClassifier<T> {
    private static final Pattern TOKEN_SPLIT = Pattern.compile("[^\\p{L}\\p{Nd}]+", Pattern.UNICODE_CHARACTER_CLASS);
    private final Map<String, Integer> tokenIndex;
    private final List<T> labels;
    private final double[][] tokenEmbeddings;
    private final double[][] wq;
    private final double[][] wk;
    private final double[][] wv;
    private final double[][] wo;
    private final double[][] w1;
    private final double[][] w2;
    private final double[][] classifierWeights;
    private final double[] classifierBias;
    private final T defaultLabel;
    private final int dModel;

    private TransformerTextClassifier(Map<String, Integer> tokenIndex,
            List<T> labels,
            double[][] tokenEmbeddings,
            double[][] wq,
            double[][] wk,
            double[][] wv,
            double[][] wo,
            double[][] w1,
            double[][] w2,
            double[][] classifierWeights,
            double[] classifierBias,
            T defaultLabel) {
        this.tokenIndex = tokenIndex;
        this.labels = labels;
        this.tokenEmbeddings = tokenEmbeddings;
        this.wq = wq;
        this.wk = wk;
        this.wv = wv;
        this.wo = wo;
        this.w1 = w1;
        this.w2 = w2;
        this.classifierWeights = classifierWeights;
        this.classifierBias = classifierBias;
        this.defaultLabel = defaultLabel;
        this.dModel = tokenEmbeddings[0].length;
    }

    public T predict(String text) {
        List<Integer> tokenIds = toTokenIds(normalizeText(text));
        if (tokenIds.isEmpty())
            return defaultLabel;

        double[][] embeddings = new double[tokenIds.size()][dModel];
        for (int i = 0; i < tokenIds.size(); i++) {
            embeddings[i] = tokenEmbeddings[tokenIds.get(i)];
        }

        double[][] encoded = encodeSequence(embeddings);
        double[] pooled = averagePooling(encoded);
        double[] logits = linearlyClassify(pooled);

        int best = argMax(logits);

        return labels.get(best);
    }

    private double[] linearlyClassify(double[] input) {
        double[] logits = new double[labels.size()];
        for (int i = 0; i < labels.size(); i++) {
            logits[i] = dot(classifierWeights[i], input) + classifierBias[i];
        }

        return logits;
    }

    private double[] averagePooling(double[][] hidden) {
        double[] pooled = new double[dModel];
        for (double[] token : hidden) {
            for (int j = 0; j < dModel; j++) {
                pooled[j] += token[j];
            }
        }

        for (int j = 0; j < dModel; j++) {
            pooled[j] /= hidden.length;
        }

        return pooled;
    }

    private double[][] encodeSequence(double[][] x) {
        double[][] q = matMul(x, wq);
        double[][] k = matMul(x, wk);
        double[][] v = matMul(x, wv);

        double[][] attention = attention(q, k, v);
        double[][] attentionResidual = add(attention, x);
        double[][] ffHidden = relu(matMul(attentionResidual, w1));
        double[][] ffOutput = matMul(ffHidden, w2);

        return add(ffOutput, attentionResidual);
    }

    private double[][] attention(double[][] q, double[][] k, double[][] v) {
        int seq = q.length;
        double scale = 1.0 / Math.sqrt(dModel);
        double[][] scores = new double[seq][seq];

        for (int i = 0; i < seq; i++) {
            for (int j = 0; j < seq; j++) {
                scores[i][j] = dot(q[i], k[j]) * scale;
            }

            softmax(scores[i]);
        }

        double[][] result = new double[seq][dModel];
        for (int i = 0; i < seq; i++) {
            for (int j = 0; j < seq; j++) {
                for (int kIndex = 0; kIndex < dModel; kIndex++) {
                    result[i][kIndex] += scores[i][j] * v[j][kIndex];
                }
            }
        }

        return matMul(result, wo);
    }

    private static double[][] matMul(double[][] a, double[][] b) {
        int rows = a.length;
        int cols = b[0].length;
        int inner = b.length;
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double sum = 0.0;
                for (int k = 0; k < inner; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }

    private static double dot(double[] a, double[] b) {
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }

        return sum;
    }

    private static double[][] add(double[][] a, double[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }

        return result;
    }

    private static void softmax(double[] row) {
        double max = Arrays.stream(row).max().orElse(0.0);
        double sum = 0.0;
        for (int i = 0; i < row.length; i++) {
            row[i] = Math.exp(row[i] - max);
            sum += row[i];
        }

        for (int i = 0; i < row.length; i++) {
            row[i] /= sum;
        }
    }

    private static double[][] relu(double[][] input) {
        int rows = input.length;
        int cols = input[0].length;
        double[][] output = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                output[i][j] = Math.max(0.0, input[i][j]);
            }
        }

        return output;
    }

    private List<Integer> toTokenIds(String text) {
        List<String> tokens = tokenize(text);
        List<Integer> ids = new ArrayList<>();
        for (String token : tokens) {
            ids.add(tokenIndex.getOrDefault(token, tokenIndex.getOrDefault("<unk>", 0)));
        }

        return ids;
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
        int dModel = 16;
        double[][] tokenEmbeddings = randomMatrix(vocabulary.size(), dModel, 0.01, 1234);
        double[][] wq = randomMatrix(dModel, dModel, 0.02, 2345);
        double[][] wk = randomMatrix(dModel, dModel, 0.02, 3456);
        double[][] wv = randomMatrix(dModel, dModel, 0.02, 4567);
        double[][] wo = randomMatrix(dModel, dModel, 0.02, 5678);
        double[][] w1 = randomMatrix(dModel, dModel, 0.02, 6789);
        double[][] w2 = randomMatrix(dModel, dModel, 0.02, 7890);

        double[][] classifierWeights = new double[labelCount][dModel];
        double[] classifierBias = new double[labelCount];
        Random random = new Random(31415);
        for (int i = 0; i < labelCount; i++) {
            for (int j = 0; j < dModel; j++) {
                classifierWeights[i][j] = random.nextGaussian() * 0.01;
            }

            classifierBias[i] = 0.0;
        }

        TransformerTextClassifier<T> classifier = new TransformerTextClassifier<>(tokenIndex, labels, tokenEmbeddings,
                wq, wk, wv, wo, w1, w2, classifierWeights, classifierBias, defaultLabel);
        classifier.trainWeights(trainingData, 150, 0.12);

        return classifier;
    }

    private void trainWeights(List<LabeledText<T>> trainingData, int epochs, double learningRate) {
        Map<T, Integer> labelIndex = new HashMap<>();
        for (int i = 0; i < labels.size(); i++) {
            labelIndex.put(labels.get(i), i);
        }

        for (int epoch = 0; epoch < epochs; epoch++) {
            for (LabeledText<T> example : trainingData) {
                List<Integer> tokenIds = toTokenIds(normalizeText(example.getText()));
                if (tokenIds.isEmpty())
                    continue;

                double[][] embeddings = new double[tokenIds.size()][dModel];
                for (int i = 0; i < tokenIds.size(); i++) {
                    embeddings[i] = tokenEmbeddings[tokenIds.get(i)];
                }

                double[][] encoded = encodeSequence(embeddings);
                double[] pooled = averagePooling(encoded);
                double[] logits = linearlyClassify(pooled);
                double[] probs = softmaxCopy(logits);
                int target = labelIndex.get(example.getLabel());
                for (int i = 0; i < labels.size(); i++) {
                    double error = ((i == target) ? 1.0 : 0.0) - probs[i];
                    for (int j = 0; j < dModel; j++) {
                        classifierWeights[i][j] += learningRate * error * pooled[j];
                    }

                    classifierBias[i] += learningRate * error;
                }
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

    private static double[][] randomMatrix(int rows, int cols, double scale, int seed) {
        double[][] matrix = new double[rows][cols];
        Random random = new Random(seed);
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
