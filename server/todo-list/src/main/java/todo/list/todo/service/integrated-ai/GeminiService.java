
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Map;

import todo.list.todo.dto.response.ParseEntitiesResponse;
import todo.list.todo.dto.response.ParsePromptResponse;
import todo.list.todo.dto.response.ParsedTaskResponse;
import todo.list.todo.entity.enums.PriorityEnum;
import todo.list.todo.entity.enums.StatusEnum;

@ApplicationScoped
public class GeminiService {

    private static final String DEFAULT_MODEL = "gemini-2.5-flash";
    private static final String API_BASE = "https://generativelanguage.googleapis.com/v1";

    private final String apiKey;
    private final String model;
    private final HttpClient client;
    private final ObjectMapper mapper;

    public GeminiService() {
        this.apiKey = "AIzaSyBmD_X3BOxUcN9MXkgFZvh_OVEyE9DWgM8";
        this.model = System.getenv().getOrDefault("GOOGLE_GEMINI_MODEL", DEFAULT_MODEL);
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();

        System.out.println("[GeminiService] API KEY loaded: " + (apiKey != null && !apiKey.isBlank()));
    }

    public boolean isEnabled() {
        return apiKey != null && !apiKey.isBlank();
    }

    public ParsePromptResponse parsePrompt(String prompt) throws IOException, InterruptedException {
        System.out.println("[GeminiService] Calling Gemini API with prompt: " + prompt);

        String requestBody = createRequestBody(prompt);
        String requestUrl = String.format(
                "%s/models/%s:generateContent?key=%s",
                API_BASE,
                model,
                apiKey);

        System.out.println("[GeminiService] Request URL: " + requestUrl);
        System.out.println("[GeminiService] Request body: " + requestBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .header("Content-Type", "application/json; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("[GeminiService] response status: " + response.statusCode());
        System.out.println("[GeminiService] response body: " + response.body());

        if (response.statusCode() != 200)
            throw new IOException(
                    "Gemini request failed: status=" + response.statusCode() + " body=" + response.body());

        JsonNode result = mapper.readTree(response.body());
        JsonNode candidates = result.path("candidates");

        if (candidates.isEmpty())
            throw new IOException("No response from Gemini");

        JsonNode candidate = candidates.path(0);
        String content = candidate
                .path("content")
                .path("parts")
                .path(0)
                .path("text")
                .asText();

        if (content.isBlank())
            content = candidate.path("output").path(0).path("content").path(0).path("text").asText();

        System.out.println("[GeminiService] raw model content: " + content);

        String json = extractJson(content);
        System.out.println("[GeminiService] extracted JSON: " + json);

        JsonNode parsed = mapper.readTree(json);

        return buildResponse(parsed, prompt);
    }

    private String createRequestBody(String prompt) throws IOException {

        String systemPrompt = """
                Bạn là AI xử lý Todo task.
                Hãy phân tích câu tiếng Việt và trả về JSON DUY NHẤT theo format:

                {
                  "intent": "CREATE | UPDATE | DELETE | GET",
                  "task": {
                    "title": "...",
                    "description": "...",
                    "dueDate": "ISO-8601",
                    "priority": "LOW | MEDIUM | HIGH",
                    "status": "PENDING | DONE",
                    "completed": false
                  },
                  "entities": {
                    "title": "...",
                    "dueDate": "...",
                    "timeExpression": "..."
                  },
                  "assistantMessage": "..."
                }

                CHỈ trả JSON. Không giải thích.
                """;
        Map<String, Object> userContent = Map.of(
                "role", "user",
                "parts", new Object[] {
                        Map.of("text", systemPrompt + "\nUser: " + prompt)
                });

        Map<String, Object> body = Map.of(
                "contents", new Object[] { userContent },
                "generationConfig", Map.of(
                        "temperature", 0,
                        "maxOutputTokens", 1024));

        return mapper.writeValueAsString(body);
    }

    private String extractJson(String content) {
        // remove markdown ```json ```
        content = content.replaceAll("```json", "")
                .replaceAll("```", "")
                .trim();

        int start = content.indexOf('{');
        int end = content.lastIndexOf('}');

        if (start >= 0 && end > start)
            return content.substring(start, end + 1);

        return content;
    }

    private ParsePromptResponse buildResponse(JsonNode parsed, String prompt) {
        String intent = parsed.path("intent").asText("CREATE");
        JsonNode taskNode = parsed.path("task");
        JsonNode entitiesNode = parsed.path("entities");

        ParsedTaskResponse task = new ParsedTaskResponse();
        task.setTitle(taskNode.path("title").asText(""));
        task.setDescription(taskNode.path("description").asText(prompt));
        task.setPriority(parsePriority(taskNode.path("priority").asText("MEDIUM")));
        task.setStatus(parseStatus(taskNode.path("status").asText("PENDING")));
        task.setCompleted(taskNode.path("completed").asBoolean(false));
        task.setDueDate(parseDueDate(taskNode.path("dueDate").asText("")));

        ParseEntitiesResponse entities = new ParseEntitiesResponse();
        entities.setTitle(entitiesNode.path("title").asText(task.getTitle()));
        entities.setDescription(entitiesNode.path("description").asText(task.getDescription()));
        entities.setPriority(entitiesNode.path("priority").asText(task.getPriority().name()));
        entities.setStatus(entitiesNode.path("status").asText(task.getStatus().name()));
        entities.setCompleted(entitiesNode.path("completed").asBoolean(task.getCompleted()));
        entities.setDueDate(entitiesNode.path("dueDate").asText(task.getDueDate().toString()));
        entities.setTimeExpression(entitiesNode.path("timeExpression").asText(null));

        String assistantMessage = parsed.path("assistantMessage").asText("");

        return new ParsePromptResponse(intent, task, entities, assistantMessage);
    }

    private PriorityEnum parsePriority(String value) {
        try {
            return PriorityEnum.valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            return PriorityEnum.MEDIUM;
        }
    }

    private StatusEnum parseStatus(String value) {
        try {
            return StatusEnum.valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            return StatusEnum.PENDING;
        }
    }

    private Instant parseDueDate(String value) {
        if (value == null || value.isBlank())
            return Instant.now();

        try {
            return Instant.parse(value);
        } catch (DateTimeParseException ignored) {
        }

        try {
            return OffsetDateTime.parse(value).toInstant();
        } catch (DateTimeParseException ignored) {
        }

        try {
            return LocalDateTime.parse(value)
                    .atZone(ZoneId.systemDefault())
                    .toInstant();
        } catch (DateTimeParseException ignored) {
        }

        try {
            return LocalDate.parse(value)
                    .atTime(18, 0)
                    .atZone(ZoneId.systemDefault())
                    .toInstant();
        } catch (DateTimeParseException ignored) {
        }

        return Instant.now();
    }
}