package todo.list.todo.service.nlp;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class OnnxBertTokenizer {
    private static final Pattern TOKEN_SPLIT = Pattern.compile("[^\\p{L}\\p{Nd}]+", Pattern.UNICODE_CHARACTER_CLASS);
    private final Map<String, Integer> vocabulary;
    private final Map<String, Integer> bpeRanks;
    private final int maxSequenceLength;

    public OnnxBertTokenizer(Path vocabPath, int maxSequenceLength) throws IOException {
        this.vocabulary = loadVocabulary(vocabPath);
        this.bpeRanks = loadBpeRanks(vocabPath.getParent().resolve("bpe.codes"));
        this.maxSequenceLength = maxSequenceLength;
    }

    public List<String> tokenize(String text) {
        if (text == null)
            return List.of();

        text = normalizeText(text);
        List<String> outputTokens = new ArrayList<>();
        for (String token : TOKEN_SPLIT.split(text)) {
            if (token.isBlank())
                continue;

            outputTokens.addAll(encodeToken(token));
            if (outputTokens.size() >= maxSequenceLength - 2)
                break;
        }

        List<String> tokens = new ArrayList<>();
        tokens.add("[CLS]");
        for (String token : outputTokens)
            tokens.add(token);
        tokens.add("[SEP]");

        if (tokens.size() > maxSequenceLength) {
            tokens = tokens.subList(0, maxSequenceLength);
            tokens.set(tokens.size() - 1, "[SEP]");
        }

        return tokens;
    }

    private List<String> encodeToken(String token) {
        if (vocabulary.containsKey(token))
            return List.of(token);

        List<String> pieces = bpe(token);
        if (pieces.isEmpty())
            return List.of("[UNK]");

        List<String> result = new ArrayList<>();
        for (String piece : pieces) {
            if (vocabulary.containsKey(piece))
                result.add(piece);
            else if (piece.endsWith("</w>") && vocabulary.containsKey(piece.substring(0, piece.length() - 4)))
                result.add(piece.substring(0, piece.length() - 4));
            else
                result.add("[UNK]");
        }

        return result;
    }

    private List<String> bpe(String token) {
        token = token + "</w>";
        List<String> parts = new ArrayList<>();
        int[] codePoints = token.codePoints().toArray();
        for (int cp : codePoints) {
            parts.add(new String(Character.toChars(cp)));
        }
        parts.set(parts.size() - 1, parts.get(parts.size() - 1) + "</w>");

        Map<String, Integer> pairs = getPairs(parts);
        while (!pairs.isEmpty()) {
            String best = null;
            int bestRank = Integer.MAX_VALUE;
            for (Map.Entry<String, Integer> entry : pairs.entrySet()) {
                if (bpeRanks.containsKey(entry.getKey()) && bpeRanks.get(entry.getKey()) < bestRank) {
                    bestRank = bpeRanks.get(entry.getKey());
                    best = entry.getKey();
                }
            }
            if (best == null)
                break;

            String[] pair = best.split(" ");
            List<String> newParts = new ArrayList<>();
            int i = 0;
            while (i < parts.size()) {
                int j = indexOfPair(parts, pair, i);
                if (j == -1) {
                    newParts.add(parts.get(i));
                    i++;
                } else {
                    for (int k = i; k < j; k++)
                        newParts.add(parts.get(k));
                    newParts.add(pair[0] + pair[1]);
                    i = j + 2;
                }
            }
            parts = newParts;
            if (parts.size() == 1)
                break;
            pairs = getPairs(parts);
        }

        return parts;
    }

    private int indexOfPair(List<String> parts, String[] pair, int start) {
        for (int i = start; i < parts.size() - 1; i++) {
            if (parts.get(i).equals(pair[0]) && parts.get(i + 1).equals(pair[1]))
                return i;
        }
        return -1;
    }

    private Map<String, Integer> getPairs(List<String> parts) {
        Map<String, Integer> pairs = new HashMap<>();
        for (int i = 0; i < parts.size() - 1; i++) {
            String pair = parts.get(i) + " " + parts.get(i + 1);
            pairs.put(pair, pairs.getOrDefault(pair, 0) + 1);
        }
        return pairs;
    }

    public long[] convertTokensToIds(List<String> tokens) {
        long[] ids = new long[maxSequenceLength];
        int index = 0;
        for (; index < tokens.size() && index < maxSequenceLength; index++) {
            ids[index] = vocabulary.getOrDefault(tokens.get(index), vocabulary.getOrDefault("[UNK]", 100));
        }

        for (; index < maxSequenceLength; index++) {
            ids[index] = vocabulary.getOrDefault("[PAD]", 0);
        }

        return ids;
    }

    public long[] buildAttentionMask(List<String> tokens) {
        long[] mask = new long[maxSequenceLength];
        for (int i = 0; i < Math.min(tokens.size(), maxSequenceLength); i++) {
            mask[i] = 1;
        }

        return mask;
    }

    public long[] buildTokenTypeIds(List<String> tokens) {
        long[] tokenTypeIds = new long[maxSequenceLength];
        return tokenTypeIds;
    }

    private static Map<String, Integer> loadBpeRanks(Path bpePath) throws IOException {
        Map<String, Integer> ranks = new HashMap<>();
        if (!Files.exists(bpePath))
            return ranks;

        try (BufferedReader reader = Files.newBufferedReader(bpePath)) {
            String line;
            int rank = 0;
            while ((line = reader.readLine()) != null) {
                line = line.strip();
                if (line.isEmpty() || line.startsWith("#"))
                    continue;
                String[] tokens = line.split("\\s+");
                if (tokens.length >= 2)
                    ranks.put(tokens[0] + " " + tokens[1], rank++);
            }
        }

        return ranks;
    }

    private static Map<String, Integer> loadVocabulary(Path vocabPath) throws IOException {
        Map<String, Integer> vocab = new HashMap<>();
        int index = 0;
        for (String line : Files.readAllLines(vocabPath, StandardCharsets.UTF_8)) {
            String trimmed = line.trim();
            if (trimmed.isEmpty())
                continue;

            String token = trimmed.split("\\s+", 2)[0];
            vocab.put(token, index++);
        }

        return vocab;
    }

    private static String normalizeText(String text) {
        if (text == null)
            return "";

        String normalized = Normalizer.normalize(text, Normalizer.Form.NFKC).toLowerCase();
        return normalized.replaceAll("\\s+", " ").trim();
    }
}
