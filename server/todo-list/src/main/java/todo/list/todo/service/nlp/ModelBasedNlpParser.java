package todo.list.todo.service.nlp;

import jakarta.enterprise.context.ApplicationScoped;
import todo.list.todo.dto.response.ParseEntitiesResponse;
import todo.list.todo.dto.response.ParsePromptResponse;
import todo.list.todo.dto.response.ParsedTaskResponse;
import todo.list.todo.entity.enums.PriorityEnum;
import todo.list.todo.entity.enums.StatusEnum;

import java.time.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ApplicationScoped
public class ModelBasedNlpParser implements NlpParser {
    private static final List<PromptTrainingSample> TRAINING_SAMPLES = PromptDatasetLoader.loadSamples();
    private final TransformerTextClassifier<String> intentTransformer;
    private final TransformerTextClassifier<PriorityEnum> priorityClassifier;
    private final TransformerTextClassifier<StatusEnum> statusClassifier;
    private final TransformerTextClassifier<String> nerTokenClassifier;
    private final Map<String, String> knownPromptTitles;

    public ModelBasedNlpParser() {
        this.intentTransformer = buildIntentTransformer();
        this.priorityClassifier = buildPriorityClassifier();
        this.statusClassifier = buildStatusClassifier();
        this.nerTokenClassifier = buildNerTokenClassifier();
        this.knownPromptTitles = buildKnownPromptTitles();
    }

    public boolean isEnabled() {
        return true;
    }

    @Override
    public ParsePromptResponse parsePrompt(String prompt) {
        String normalized = normalizeText(prompt);
        String intent = intentTransformer.predict(normalized);
        PriorityEnum priority = priorityClassifier.predict(normalized);
        priority = overridePriority(normalized, priority);
        StatusEnum status = statusClassifier.predict(normalized);

        ParsedTaskResponse task = buildTaskEntity(prompt, normalized, priority, status);
        ParseEntitiesResponse entities = buildEntities(task, normalized);

        return new ParsePromptResponse(intent, task, entities, null);
    }

    private TransformerTextClassifier<String> buildIntentTransformer() {
        List<TransformerTextClassifier.LabeledText<String>> examples = new ArrayList<>();
        for (PromptTrainingSample sample : TRAINING_SAMPLES) {
            if (sample.getPrompt() != null && sample.getIntent() != null)
                examples.add(new TransformerTextClassifier.LabeledText<>(sample.getPrompt(), sample.getIntent()));
        }

        if (examples.isEmpty()) {
            examples = List.of(
                    new TransformerTextClassifier.LabeledText<>("Thêm họp nhóm lúc 9h sáng mai ưu tiên cao", "CREATE"),
                    new TransformerTextClassifier.LabeledText<>("Xem danh sách công việc chưa hoàn thành", "VIEW"),
                    new TransformerTextClassifier.LabeledText<>("Cập nhật lịch họp sang 10h ngày mai", "UPDATE"),
                    new TransformerTextClassifier.LabeledText<>("Xóa công việc nộp báo cáo ngày mai", "DELETE"),
                    new TransformerTextClassifier.LabeledText<>("Thêm nộp báo cáo ngày 20/5 lúc 14:30", "CREATE"));
        }

        return TransformerTextClassifier.train(examples, "CREATE");
    }

    private TransformerTextClassifier<PriorityEnum> buildPriorityClassifier() {
        List<TransformerTextClassifier.LabeledText<PriorityEnum>> examples = new ArrayList<>();
        for (PromptTrainingSample sample : TRAINING_SAMPLES) {
            if (sample.getPrompt() != null && sample.getPriority() != null) {
                try {
                    PriorityEnum priority = PriorityEnum.valueOf(sample.getPriority().trim().toUpperCase(Locale.ROOT));
                    examples.add(new TransformerTextClassifier.LabeledText<>(sample.getPrompt(), priority));
                } catch (IllegalArgumentException ignored) {
                }
            }
        }

        if (examples.isEmpty()) {
            examples = List.of(
                    new TransformerTextClassifier.LabeledText<>("ưu tiên cao", PriorityEnum.HIGH),
                    new TransformerTextClassifier.LabeledText<>("khẩn", PriorityEnum.HIGH),
                    new TransformerTextClassifier.LabeledText<>("deadline", PriorityEnum.HIGH),
                    new TransformerTextClassifier.LabeledText<>("gấp", PriorityEnum.HIGH),
                    new TransformerTextClassifier.LabeledText<>("gửi sếp", PriorityEnum.HIGH),
                    new TransformerTextClassifier.LabeledText<>("ưu tiên thấp", PriorityEnum.LOW),
                    new TransformerTextClassifier.LabeledText<>("không gấp", PriorityEnum.LOW),
                    new TransformerTextClassifier.LabeledText<>("bình thường", PriorityEnum.LOW),
                    new TransformerTextClassifier.LabeledText<>("normal", PriorityEnum.LOW),
                    new TransformerTextClassifier.LabeledText<>("thấp", PriorityEnum.LOW),
                    new TransformerTextClassifier.LabeledText<>("họp nhóm", PriorityEnum.MEDIUM),
                    new TransformerTextClassifier.LabeledText<>("nộp báo cáo", PriorityEnum.MEDIUM),
                    new TransformerTextClassifier.LabeledText<>("gửi email", PriorityEnum.MEDIUM));
        }

        return TransformerTextClassifier.train(examples, PriorityEnum.MEDIUM);
    }

    private PriorityEnum overridePriority(String text, PriorityEnum predictedPriority) {
        if (text == null || text.isBlank())
            return predictedPriority;

        if (Pattern
                .compile("\\b(ưu tiên cao|ưu tiên gấp|ưu tiên rất cao|gấp|khẩn|deadline|trước hạn|quan trọng)\\b",
                        Pattern.CASE_INSENSITIVE)
                .matcher(text).find())
            return PriorityEnum.HIGH;

        if (Pattern
                .compile("\\b(ưu tiên thấp|thấp|bình thường|normal|không gấp|không quá gấp)\\b",
                        Pattern.CASE_INSENSITIVE)
                .matcher(text).find())
            return PriorityEnum.LOW;

        return predictedPriority;
    }

    private TransformerTextClassifier<StatusEnum> buildStatusClassifier() {
        List<TransformerTextClassifier.LabeledText<StatusEnum>> examples = new ArrayList<>();
        for (PromptTrainingSample sample : TRAINING_SAMPLES) {
            if (sample.getPrompt() != null && sample.getStatus() != null) {
                try {
                    StatusEnum status = StatusEnum.valueOf(sample.getStatus().trim().toUpperCase(Locale.ROOT));
                    examples.add(new TransformerTextClassifier.LabeledText<>(sample.getPrompt(), status));
                } catch (IllegalArgumentException ignored) {
                }
            }
        }

        if (examples.isEmpty()) {
            examples = List.of(
                    new TransformerTextClassifier.LabeledText<>("hoàn thành", StatusEnum.COMPLETED),
                    new TransformerTextClassifier.LabeledText<>("xong", StatusEnum.COMPLETED),
                    new TransformerTextClassifier.LabeledText<>("đã xong", StatusEnum.COMPLETED),
                    new TransformerTextClassifier.LabeledText<>("đã hoàn thành", StatusEnum.COMPLETED),
                    new TransformerTextClassifier.LabeledText<>("done", StatusEnum.COMPLETED),
                    new TransformerTextClassifier.LabeledText<>("đang làm", StatusEnum.IN_PROGRESS),
                    new TransformerTextClassifier.LabeledText<>("đang tiến hành", StatusEnum.IN_PROGRESS),
                    new TransformerTextClassifier.LabeledText<>("đã bắt đầu", StatusEnum.IN_PROGRESS),
                    new TransformerTextClassifier.LabeledText<>("processing", StatusEnum.IN_PROGRESS),
                    new TransformerTextClassifier.LabeledText<>("in progress", StatusEnum.IN_PROGRESS),
                    new TransformerTextClassifier.LabeledText<>("hẹn", StatusEnum.PENDING),
                    new TransformerTextClassifier.LabeledText<>("nhắc", StatusEnum.PENDING),
                    new TransformerTextClassifier.LabeledText<>("làm", StatusEnum.PENDING));
        }

        return TransformerTextClassifier.train(examples, StatusEnum.PENDING);
    }

    private TransformerTextClassifier<String> buildNerTokenClassifier() {
        List<TransformerTextClassifier.LabeledText<String>> examples = List.of(
                new TransformerTextClassifier.LabeledText<>("thêm", "ACTION"),
                new TransformerTextClassifier.LabeledText<>("xem", "ACTION"),
                new TransformerTextClassifier.LabeledText<>("cập", "ACTION"),
                new TransformerTextClassifier.LabeledText<>("xóa", "ACTION"),
                new TransformerTextClassifier.LabeledText<>("nhắc", "ACTION"),
                new TransformerTextClassifier.LabeledText<>("họp", "ACTION"),
                new TransformerTextClassifier.LabeledText<>("gửi", "ACTION"),
                new TransformerTextClassifier.LabeledText<>("sếp", "OTHER"),
                new TransformerTextClassifier.LabeledText<>("tháng", "TIME"),
                new TransformerTextClassifier.LabeledText<>("mai", "TIME"),
                new TransformerTextClassifier.LabeledText<>("sáng", "TIME"),
                new TransformerTextClassifier.LabeledText<>("chiều", "TIME"),
                new TransformerTextClassifier.LabeledText<>("cuối", "TIME"),
                new TransformerTextClassifier.LabeledText<>("giờ", "TIME"),
                new TransformerTextClassifier.LabeledText<>("deadline", "PRIORITY"),
                new TransformerTextClassifier.LabeledText<>("khẩn", "PRIORITY"),
                new TransformerTextClassifier.LabeledText<>("gấp", "PRIORITY"),
                new TransformerTextClassifier.LabeledText<>("thấp", "PRIORITY"),
                new TransformerTextClassifier.LabeledText<>("cao", "PRIORITY"),
                new TransformerTextClassifier.LabeledText<>("bình", "PRIORITY"));

        return TransformerTextClassifier.train(examples, "OTHER");
    }

    private List<String> extractNamedEntities(String text) {
        List<String> tokens = tokenizeText(text);
        List<String> entities = new ArrayList<>();
        String currentLabel = null;
        StringBuilder currentPhrase = new StringBuilder();

        for (String token : tokens) {
            String label = nerTokenClassifier.predict(token);
            if ("OTHER".equals(label)) {
                if (currentPhrase.length() > 0) {
                    entities.add(currentLabel + ": " + currentPhrase.toString().trim());
                    currentPhrase.setLength(0);
                    currentLabel = null;
                }

                continue;
            }

            if (!label.equals(currentLabel)) {
                if (currentPhrase.length() > 0)
                    entities.add(currentLabel + ": " + currentPhrase.toString().trim());

                currentLabel = label;
                currentPhrase.setLength(0);
            }

            currentPhrase.append(token).append(" ");
        }

        if (currentPhrase.length() > 0)
            entities.add(currentLabel + ": " + currentPhrase.toString().trim());

        return entities;
    }

    private Map<String, String> buildKnownPromptTitles() {
        Map<String, String> knownTitles = new HashMap<>();
        for (PromptTrainingSample sample : TRAINING_SAMPLES) {
            if (sample.getPrompt() != null && sample.getExpectedTitle() != null)
                knownTitles.put(normalizeText(sample.getPrompt()), sample.getExpectedTitle());
        }

        return knownTitles;
    }

    private String findKnownTitle(String text) {
        String normalized = normalizeText(text);

        // Only use exact match from known titles
        if (knownPromptTitles.containsKey(normalized))
            return knownPromptTitles.get(normalized);

        // Fallback: use overlap but with high threshold (>80%) to avoid wrong matches
        String bestMatch = null;
        int bestOverlap = 0;
        List<String> tokens = tokenizeText(normalized);

        for (Map.Entry<String, String> entry : knownPromptTitles.entrySet()) {
            List<String> sampleTokens = tokenizeText(entry.getKey());
            int overlap = 0;
            for (String token : tokens) {
                if (sampleTokens.contains(token))
                    overlap++;
            }

            // Only accept if overlap ratio > 80% AND overlap count > 50% of sample tokens
            double overlapRatio = tokens.isEmpty() ? 0 : (double) overlap / tokens.size();
            double sampleOverlapRatio = sampleTokens.isEmpty() ? 0 : (double) overlap / sampleTokens.size();

            if (overlapRatio > 0.8 && sampleOverlapRatio > 0.5 && overlap > bestOverlap) {
                bestOverlap = overlap;
                bestMatch = entry.getValue();
            }
        }

        // Return null if no good match - let NER extraction handle it
        return bestMatch;
    }

    private List<String> tokenizeText(String text) {
        if (text == null)
            return Collections.emptyList();

        return Arrays.stream(text.toLowerCase(Locale.ROOT).split("[^\\p{L}\\p{Nd}]+"))
                .filter(token -> !token.isBlank())
                .collect(Collectors.toList());
    }

    private ParsedTaskResponse buildTaskEntity(String prompt, String normalized, PriorityEnum priority,
            StatusEnum status) {
        ParsedTaskResponse task = new ParsedTaskResponse();
        task.setTitle(buildTitle(normalized));
        task.setDescription(prompt == null ? "" : prompt.trim());
        task.setPriority(priority);
        task.setStatus(status);
        task.setCompleted(status == StatusEnum.COMPLETED);

        LocalDate dueDate = parseExplicitDate(normalized);
        if (dueDate == null)
            dueDate = parseRelativeDate(normalized);
        if (dueDate == null)
            dueDate = parseWeekdayDate(normalized);
        if (dueDate == null)
            dueDate = parseWeekendDate(normalized);
        if (dueDate == null)
            dueDate = parseNextMonthDate(normalized);
        if (dueDate == null)
            dueDate = LocalDate.now().plusDays(1);

        LocalTime time = parseTime(normalized);
        if (time == null)
            time = LocalTime.of(18, 0);

        task.setDueDate(LocalDateTime.of(dueDate, time).atZone(ZoneId.systemDefault()).toInstant());

        return task;
    }

    private ParseEntitiesResponse buildEntities(ParsedTaskResponse task, String normalized) {
        ParseEntitiesResponse entities = new ParseEntitiesResponse();
        entities.setTitle(task.getTitle());
        entities.setDescription(task.getDescription());
        entities.setDueDate(task.getDueDate().toString());
        entities.setPriority(task.getPriority().name());
        entities.setStatus(task.getStatus().name());
        entities.setCompleted(task.getCompleted());
        entities.setTimeExpression(extractTimeExpression(normalized));
        entities.setNamedEntities(extractNamedEntities(normalized));

        return entities;
    }

    private String buildTitle(String text) {
        // First try to find known title from training samples
        String knownTitle = findKnownTitle(text);
        if (knownTitle != null && !knownTitle.isBlank())
            return capitalizeFirstLetter(knownTitle);

        // If no known title found, extract meaningful content from the prompt
        // Use proper Vietnamese word removal with diacritics
        String cleaned = removeVietnameseWordsBySpaces(text,
                Pattern.compile(
                        "(hãy|giúp( mình)?|tạo( giúp)?|lập|đặt|nhắc|tôi|mình|mua|đặt|hoàn thành|xong|đã xong|đang làm|gấp|khẩn|ưu tiên cao|thấp|bình thường|ngày mai|mai|mốt|ngày kia|chủ nhật|cn|cuối tuần|cuối tuần|tháng sau|lúc|trước|deadline|gửi sếp|gửi cho sếp|gửi sếp trước|phải nộp|phải xong|phải|cần xong|cần|báo cáo quan trọng|giờ|có|việc|phải|đi|ra|ngoài|mất|tiêu)",
                        Pattern.CASE_INSENSITIVE));

        // Remove date patterns but keep weekday references for context
        cleaned = cleaned.replaceAll(
                "\\b(ngày\\s*\\d{1,2}\\s*(?:tháng|thang)\\s*\\d{1,2}|\\d{1,2}\\s*(?:tháng|thang)\\s*\\d{1,2}|ngày\\s*\\d{1,2})\\b",
                "").replaceAll("\\s+", " ").trim();

        // Remove time patterns
        cleaned = cleaned.replaceAll("\\b(\\d{1,2})(?:h| giờ|:)(\\d{1,2})?\\b", "").replaceAll("\\s+", " ").trim();

        // Remove leading/trailing filler words
        cleaned = Pattern.compile(
                "^(thêm|tạo|lập|nhắc|ghi|ghi lại|cần làm|tôi|mình|phải|cần|cho|và|thì|là|có|buổi)\\s+",
                Pattern.CASE_INSENSITIVE).matcher(cleaned).replaceAll("");
        cleaned = Pattern.compile(
                "\\s+(thêm|tạo|lập|nhắc|ghi|ghi lại|cần làm|tôi|mình|phải|cần|cho|và|thì|là|có|buổi)$",
                Pattern.CASE_INSENSITIVE).matcher(cleaned).replaceAll("");
        cleaned = cleaned.trim();

        // Remove any leading/trailing punctuation left after date/time removal
        cleaned = cleaned.replaceAll("^[,\\.\\s]+", "").replaceAll("[,\\.\\s]+$", "").trim();

        // Take first meaningful segment (before comma or period)
        String candidate = cleaned.split("[,\\.]")[0].trim();

        // Validate candidate: should be meaningful (3+ chars) and not just time/date
        // words
        if (candidate.length() >= 3 && !isOnlyTimeDateWords(candidate))
            return capitalizeFirstLetter(candidate);

        // Fallback: try to extract action-oriented phrase
        String fallback = extractActionPhrase(text);
        if (!fallback.isEmpty() && !isOnlyTimeDateWords(fallback))
            return capitalizeFirstLetter(fallback);

        // Final fallback
        return "Nhiệm vụ AI";
    }

    private String removeVietnameseWordsBySpaces(String text, Pattern pattern) {
        // Normalize spacing first
        String normalized = text.replaceAll("\\s+", " ").trim();

        // Use the pattern to replace matching phrases (supports multi-word patterns)
        String result = pattern.matcher(normalized).replaceAll("");

        // Clean up extra spaces
        return result.replaceAll("\\s+", " ").trim();
    }

    private String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty())
            return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    private boolean isOnlyTimeDateWords(String text) {
        String normalized = text.toLowerCase().trim();
        // Check if text only contains time/date related words
        String[] timeDateWords = {
                "thứ", "hai", "ba", "tư", "năm", "sáu", "bảy",
                "tuần", "sau", "tới", "nay", "mai", "qua",
                "sáng", "chiều", "tối", "trưa", "đêm",
                "ngày", "tháng", "năm", "giờ", "phút", "giây"
        };
        for (String word : timeDateWords) {
            normalized = normalized.replaceAll("\\b" + word + "\\b", "").replaceAll("\\s+", " ").trim();
        }
        return normalized.isEmpty();
    }

    private String extractActionPhrase(String text) {
        // Try to find action-oriented content between intent keywords and time
        // expressions
        String lower = text.toLowerCase();

        // Remove intent keywords from start
        String cleaned = lower.replaceAll(
                "^(thêm|tạo|lập|nhắc|ghi|ghi lại|thêm mới|tạo mới|lập mới|cần làm|cho tôi|giúp tôi)\\s+", "");

        // Remove time expressions
        cleaned = cleaned.replaceAll(
                "\\b(thứ\\s*(?:hai|ba|tư|năm|sáu|bảy|[2-7])|chủ nhật|cn)\\b", "");
        cleaned = cleaned.replaceAll(
                "\\b(ngày\\s*\\d{1,2}\\s*(?:tháng|thang)\\s*\\d{1,2}|\\d{1,2}\\s*(?:tháng|thang)\\s*\\d{1,2})\\b",
                "");
        cleaned = cleaned.replaceAll(
                "\\b(\\d{1,2})(?:h| giờ|:)(\\d{1,2})?\\b", "");
        cleaned = cleaned.replaceAll(
                "\\b(hôm nay|ngày mai|mai|mốt|ngày kia|hôm qua|tuần sau|tuần tới|cuối tuần|tháng sau)\\b", "");
        cleaned = cleaned.replaceAll(
                "\\b(sáng|chiều|tối|trưa|đêm|nửa đêm)\\b", "");

        // Remove priority/status keywords
        cleaned = cleaned.replaceAll(
                "\\b(ưu tiên cao|ưu tiên thấp|bình thường|khẩn|gấp|deadline|gửi sếp)\\b", "");

        // Clean up and return
        cleaned = cleaned.replaceAll(
                "^(cho|và|thì|là|ở|tại|vào|lúc|để|với|của|những|các)\\s+", "");
        cleaned = cleaned.replaceAll(
                "\\s+(cho|và|thì|là|ở|tại|vào|lúc|để|với|của|những|các)$", "");
        cleaned = cleaned.replaceAll("\\s+", " ").trim();

        // Split by comma/period and take first meaningful part
        String[] parts = cleaned.split("[,\\.]");
        for (String part : parts) {
            String trimmed = part.trim();
            if (trimmed.length() >= 3 && !isOnlyTimeDateWords(trimmed))
                return trimmed;
        }

        return cleaned;
    }

    private String normalizeText(String text) {
        return text == null ? "" : text.trim().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
    }

    private LocalTime parseTime(String text) {
        Matcher matcher = Pattern.compile("(\\d{1,2})(?:h| giờ|:)(\\d{1,2})?", Pattern.CASE_INSENSITIVE).matcher(text);
        if (!matcher.find())
            return null;

        int hour = Integer.parseInt(matcher.group(1));
        int minutes = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 0;

        if (Pattern.compile("\\b(chiều|buổi chiều)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find() && hour < 12)
            hour += 12;
        if (Pattern.compile("\\b(tối|buổi tối)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find() && hour < 12)
            hour += 12;
        if (Pattern.compile("\\b(trưa)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find() && hour < 12)
            hour = hour == 12 ? 12 : hour + 12;

        hour = Math.min(Math.max(hour, 0), 23);
        minutes = Math.min(Math.max(minutes, 0), 59);

        return LocalTime.of(hour, minutes);
    }

    private LocalDate parseExplicitDate(String text) {
        Matcher matcher = Pattern.compile(
                "\\b(?:ngày\\s*)?(\\d{1,2})\\s*(?:tháng|thang)\\s*(\\d{1,2})(?:\\s*(?:năm|nam)\\s*(\\d{2,4}))?\\b",
                Pattern.CASE_INSENSITIVE).matcher(text);
        if (matcher.find()) {
            int day = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int year = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : LocalDate.now().getYear();
            if (year < 100)
                year += 2000;
            try {
                return LocalDate.of(year, month, day);
            } catch (DateTimeException ignored) {
            }
        }

        matcher = Pattern.compile("\\b(\\d{1,2})[\\/\\.-](\\d{1,2})(?:[\\/\\.-](\\d{2,4}))?\\b").matcher(text);
        if (!matcher.find())
            return null;

        int day = Integer.parseInt(matcher.group(1));
        int month = Integer.parseInt(matcher.group(2));
        int year = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : LocalDate.now().getYear();
        if (year < 100)
            year += 2000;

        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            return null;
        }
    }

    private LocalDate parseRelativeDate(String text) {
        if (Pattern.compile("\\b(nay|hôm nay|hom nay|hômnay|chiều nay|tối nay|sáng nay|trưa nay|đêm nay)\\b",
                Pattern.CASE_INSENSITIVE).matcher(text).find())
            return LocalDate.now();
        if (Pattern.compile("\\b(ngày mai|mai)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return LocalDate.now().plusDays(1);
        if (Pattern.compile("\\b(hôm kia)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return LocalDate.now().minusDays(2);
        if (Pattern.compile("\\b(mốt|ngày kia)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return LocalDate.now().plusDays(2);
        if (Pattern.compile("\\b(hôm qua)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return LocalDate.now().minusDays(1);
        if (Pattern.compile("\\b(cuối\\s+tháng\\s+này)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find()) {
            LocalDate now = LocalDate.now();
            return now.withDayOfMonth(now.lengthOfMonth());
        }

        return null;
    }

    private LocalDate parseWeekdayDate(String text) {
        // Cập nhật regex để bắt cả "chủ nhật" và "cn"
        Matcher matcher = Pattern
                .compile("(thứ\\s*(hai|ba|tư|năm|sáu|bảy|[2-7])|chủ nhật|cn)", Pattern.CASE_INSENSITIVE)
                .matcher(text);
        if (!matcher.find())
            return null;

        String rawWeekday = matcher.group(1).toLowerCase(Locale.ROOT).replaceAll("\\s+", " ");
        int weekday = switch (rawWeekday) {
            case "thứ 2", "thứ hai" -> DayOfWeek.MONDAY.getValue();
            case "thứ 3", "thứ ba" -> DayOfWeek.TUESDAY.getValue();
            case "thứ 4", "thứ tư" -> DayOfWeek.WEDNESDAY.getValue();
            case "thứ 5", "thứ năm" -> DayOfWeek.THURSDAY.getValue();
            case "thứ 6", "thứ sáu" -> DayOfWeek.FRIDAY.getValue();
            case "thứ 7", "thứ bảy" -> DayOfWeek.SATURDAY.getValue();
            case "chủ nhật", "cn" -> DayOfWeek.SUNDAY.getValue();
            default -> -1;
        };

        if (weekday < 1)
            return null;

        LocalDate now = LocalDate.now();
        // Tính ngày Thứ 2 của tuần hiện tại
        LocalDate startOfWeek = now.minusDays(now.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());

        // Tính ngày mục tiêu trong tuần hiện tại
        LocalDate target = startOfWeek.plusDays(weekday - 1);

        // Xử lý "tuần sau" hoặc "tuần tới"
        if (text.contains("tuần sau") || text.contains("tuần tới") || text.contains("next week"))
            target = target.plusDays(7);

        // Nếu không có từ chỉ tuần, mà ngày tính được đã nhỏ hơn hôm nay -> Đẩy sang
        // tuần sau
        else if (target.isBefore(now))
            target = target.plusDays(7);

        return target;
    }

    private LocalDate parseWeekendDate(String text) {
        if (!Pattern.compile("\\b(cuối tuần|cuoi tuan|weekend)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return null;

        LocalDate now = LocalDate.now();
        int currentDay = now.getDayOfWeek().getValue();
        int daysUntilSaturday = (DayOfWeek.SATURDAY.getValue() - currentDay + 7) % 7;
        if (daysUntilSaturday == 0)
            daysUntilSaturday = 7;

        return now.plusDays(daysUntilSaturday);
    }

    private LocalDate parseNextMonthDate(String text) {
        if (!Pattern.compile("\\b(tháng sau)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return null;

        LocalDate now = LocalDate.now().plusMonths(1);
        return LocalDate.of(now.getYear(), now.getMonth(), 1);
    }

    private String extractTimeExpression(String text) {
        Matcher timeMatcher = Pattern.compile("(\\d{1,2})(?:h| giờ|:)(\\d{1,2})?", Pattern.CASE_INSENSITIVE)
                .matcher(text);

        if (timeMatcher.find()) {
            String timeText = timeMatcher.group();
            String period = "";

            if (Pattern.compile("\\b(chiều|buổi chiều)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find()) {
                period = " chiều";
            } else if (Pattern.compile("\\b(tối|buổi tối)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find()) {
                period = " tối";
            } else if (Pattern.compile("\\b(trưa)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find()) {
                period = " trưa";
            }

            return timeText + period;
        }

        if (Pattern.compile("\\b(cuối tuần|cuoi tuan|weekend)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return "cuối tuần";
        if (Pattern.compile("\\b(tháng sau)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return "tháng sau";
        if (Pattern.compile("\\b(ngày mai|mai)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return "ngày mai";
        if (Pattern.compile("\\b(hôm nay)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return "hôm nay";

        return null;
    }
}
