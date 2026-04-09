package todo.list.todo.service.nlp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PromptDatasetLoader {
    private static final Path DEFAULT_DATASET_PATH = Path.of(System.getProperty("user.dir"))
            .resolve("..")
            .resolve("..")
            .resolve("docs")
            .resolve("datasets")
            .resolve("prompt-dataset.md")
            .normalize();

    public static List<PromptTrainingSample> loadSamples() {
        String configuredPath = System.getenv().getOrDefault("PROMPT_DATASET_PATH", DEFAULT_DATASET_PATH.toString());
        Path path = Path.of(configuredPath);
        if (!Files.exists(path))
            return Collections.emptyList();

        List<PromptTrainingSample> samples = new ArrayList<>();
        String prompt = null;
        String intent = null;
        String priority = null;
        String status = null;
        String expectedTitle = null;
        String expectedDueDate = null;
        String notes = null;

        try {
            for (String rawLine : Files.readAllLines(path, StandardCharsets.UTF_8)) {
                String line = rawLine.trim();
                if (line.startsWith("- prompt:")) {
                    if (prompt != null && intent != null) {
                        samples.add(new PromptTrainingSample(prompt, intent, priority, status, expectedTitle,
                                expectedDueDate, notes));
                    }
                    prompt = extractValue(line);
                    intent = null;
                    priority = null;
                    status = null;
                    expectedTitle = null;
                    expectedDueDate = null;
                    notes = null;
                    continue;
                }

                if (line.startsWith("- intent:")) {
                    intent = extractValue(line);
                    continue;
                }

                if (line.startsWith("- expected priority:")) {
                    priority = extractValue(line);
                    continue;
                }

                if (line.startsWith("- expected status:")) {
                    status = extractValue(line);
                    continue;
                }

                if (line.startsWith("- expected title:")) {
                    expectedTitle = extractValue(line);
                    continue;
                }

                if (line.startsWith("- expected dueDate:")) {
                    expectedDueDate = extractValue(line);
                    continue;
                }

                if (line.startsWith("- expected notes:")) {
                    notes = extractValue(line);
                    continue;
                }

                if (line.isEmpty() && prompt != null && intent != null) {
                    samples.add(new PromptTrainingSample(prompt, intent, priority, status, expectedTitle,
                            expectedDueDate, notes));
                    prompt = null;
                    intent = null;
                    priority = null;
                    status = null;
                    expectedTitle = null;
                    expectedDueDate = null;
                    notes = null;
                }
            }
        } catch (IOException e) {
            return Collections.emptyList();
        }

        if (prompt != null && intent != null)
            samples.add(
                    new PromptTrainingSample(prompt, intent, priority, status, expectedTitle, expectedDueDate, notes));

        return samples;
    }

    private static String extractValue(String line) {
        int colon = line.indexOf(':');
        if (colon < 0)
            return line.trim();

        return line.substring(colon + 1).replaceAll("[`\\\\\"]", "").trim();
    }
}
