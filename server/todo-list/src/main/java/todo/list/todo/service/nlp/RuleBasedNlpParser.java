package todo.list.todo.service.nlp;

import jakarta.enterprise.context.ApplicationScoped;
import todo.list.todo.dto.response.ParseEntitiesResponse;
import todo.list.todo.dto.response.ParsePromptResponse;
import todo.list.todo.dto.response.ParsedTaskResponse;
import todo.list.todo.entity.enums.PriorityEnum;
import todo.list.todo.entity.enums.StatusEnum;

import java.time.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class RuleBasedNlpParser implements NlpParser {
    private static final Pattern CREATE_PATTERN = Pattern.compile(
            "\\b(thêm|tạo|lập|nhắc|ghi|ghi lại|thêm mới|tạo mới|lập mới|cần làm)\\b", Pattern.CASE_INSENSITIVE);
    private static final Pattern UPDATE_PATTERN = Pattern.compile("\\b(sửa|cập nhật|chỉnh sửa|thay đổi|đổi)\\b",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern DELETE_PATTERN = Pattern.compile("\\b(xóa|xoá|hủy|huy|gỡ)\\b",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern VIEW_PATTERN = Pattern.compile("\\b(xem|hiển thị|kiểm tra|tìm|list|danh sách)\\b",
            Pattern.CASE_INSENSITIVE);

    private static final Pattern PRIORITY_HIGH_PATTERN = Pattern
            .compile(
                    "\\b(khẩn|gấp|ưu tiên cao|rất quan trọng|cấp bách|urgent|deadline|gửi sếp|phải nộp|phải xong|cần xong|gửi cho sếp|gửi sếp trước|báo cáo quan trọng|cuối hạn)\\b",
                    Pattern.CASE_INSENSITIVE);
    private static final Pattern PRIORITY_LOW_PATTERN = Pattern
            .compile("\\b(thấp|không gấp|bình thường|normal|thường)\\b", Pattern.CASE_INSENSITIVE);

    private static final Pattern TITLE_REMOVE_PATTERN = Pattern.compile(
            "\\b(hãy|giúp( mình)?|tạo( giúp)?|lập|đặt|nhắc|tôi|mình|mua|đặt|hoàn thành|xong|đã xong|đang làm|gấp|khẩn|ưu tiên cao|thấp|bình thường|ngày mai|mai|hôm nay|mốt|ngày kia|thứ\\s*(?:hai|ba|tư|năm|sáu|bảy|[2-7])|chủ nhật|cn|tuần sau|cuối tuần|cuoi tuan|tháng sau|chiều|sáng|tối|trưa|lúc|trước|deadline|gửi sếp|gửi cho sếp|gửi sếp trước|phải nộp|phải xong|phải|cần xong|cần|báo cáo quan trọng|giờ)\\b",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern TITLE_TIME_PATTERN = Pattern.compile("\\b(\\d{1,2})(?:h| giờ|:)(\\d{1,2})?\\b",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern TITLE_DATE_PATTERN = Pattern.compile(
            "\\b(ngày\\s*\\d{1,2}\\s*(?:tháng|thang)\\s*\\d{1,2}|\\d{1,2}\\s*(?:tháng|thang)\\s*\\d{1,2}|ngày\\s*\\d{1,2})\\b",
            Pattern.CASE_INSENSITIVE);

    private static final Pattern STATUS_COMPLETED_PATTERN = Pattern
            .compile("\\b(xong|hoàn thành|hoàn tất|done|đã xong|đã hoàn thành)\\b", Pattern.CASE_INSENSITIVE);
    private static final Pattern STATUS_IN_PROGRESS_PATTERN = Pattern
            .compile("\\b(đang làm|đang tiến hành|đã bắt đầu|processing|in progress)\\b", Pattern.CASE_INSENSITIVE);

    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2})(?:h| giờ|:)(\\d{1,2})?",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern EXPLICIT_DATE_PATTERN = Pattern
            .compile("\\b(\\d{1,2})[\\/\\.-](\\d{1,2})(?:[\\/\\.-](\\d{2,4}))?\\b");
    private static final Pattern EXPLICIT_DATE_TEXT_PATTERN = Pattern.compile(
            "\\b(?:ngày\\s*)?(\\d{1,2})\\s*(?:tháng|thang)\\s*(\\d{1,2})(?:\\s*(?:năm|nam)\\s*(\\d{2,4}))?\\b",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern WEEKDAY_PATTERN = Pattern
            .compile("(thứ\\s*(?:hai|ba|tư|năm|sáu|bảy|[2-7])|chủ nhật|cn)", Pattern.CASE_INSENSITIVE);
    private static final Pattern WEEKEND_PATTERN = Pattern.compile("\\b(cuối tuần|cuoi tuan|weekend)\\b",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern NEXT_MONTH_PATTERN = Pattern.compile("\\b(tháng sau)\\b", Pattern.CASE_INSENSITIVE);

    @Override
    public ParsePromptResponse parsePrompt(String prompt) {
        String normalized = normalizeText(prompt);
        String intent = parseIntent(normalized);
        ParsedTaskResponse task = buildTaskEntity(prompt, normalized);
        ParseEntitiesResponse entities = buildEntities(task, normalized);

        return new ParsePromptResponse(intent, task, entities, null);
    }

    private String parseIntent(String text) {
        if (DELETE_PATTERN.matcher(text).find())
            return "DELETE";

        if (UPDATE_PATTERN.matcher(text).find())
            return "UPDATE";

        if (VIEW_PATTERN.matcher(text).find())
            return "VIEW";

        if (CREATE_PATTERN.matcher(text).find())
            return "CREATE";

        return "CREATE";
    }

    private PriorityEnum parsePriority(String text) {
        if (PRIORITY_HIGH_PATTERN.matcher(text).find())
            return PriorityEnum.HIGH;

        if (PRIORITY_LOW_PATTERN.matcher(text).find())
            return PriorityEnum.LOW;

        return PriorityEnum.MEDIUM;
    }

    private StatusEnum parseStatus(String text) {
        if (STATUS_COMPLETED_PATTERN.matcher(text).find())
            return StatusEnum.COMPLETED;

        if (STATUS_IN_PROGRESS_PATTERN.matcher(text).find())
            return StatusEnum.IN_PROGRESS;

        return StatusEnum.PENDING;
    }

    private LocalTime parseTime(String text) {
        Matcher matcher = TIME_PATTERN.matcher(text);
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
        Matcher matcher = EXPLICIT_DATE_TEXT_PATTERN.matcher(text);
        if (matcher.find()) {
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

        matcher = EXPLICIT_DATE_PATTERN.matcher(text);
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
        if (Pattern.compile("\\b(nay|hôm nay|hom nay|hômnay)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return LocalDate.now();

        if (Pattern.compile("\\b(ngày mai|mai)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return LocalDate.now().plusDays(1);

        if (Pattern.compile("\\b(mốt|ngày kia)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return LocalDate.now().plusDays(2);

        if (Pattern.compile("\\b(hôm qua)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return LocalDate.now().minusDays(1);

        return null;
    }

    private LocalDate parseWeekdayDate(String text) {
        Matcher matcher = WEEKDAY_PATTERN.matcher(text);
        if (!matcher.find())
            return null;

        String rawWeekday = matcher.group(1).toLowerCase();
        int weekday = switch (rawWeekday.replaceAll("\\s+", " ")) {
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

        LocalDate currentDate = LocalDate.now();
        int currentDayOfWeek = currentDate.getDayOfWeek().getValue();
        int daysUntil = (weekday - currentDayOfWeek + 7) % 7;
        if (daysUntil == 0)
            daysUntil = 7;

        if (Pattern.compile("\\b(tuần sau|sau tuần|next week)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            daysUntil += 7;

        return currentDate.plusDays(daysUntil);
    }

    private LocalDate parseWeekendDate(String text) {
        if (!WEEKEND_PATTERN.matcher(text).find())
            return null;

        LocalDate now = LocalDate.now();
        int currentDay = now.getDayOfWeek().getValue();
        int daysUntilSaturday = (DayOfWeek.SATURDAY.getValue() - currentDay + 7) % 7;
        if (daysUntilSaturday == 0)
            daysUntilSaturday = 7;

        return now.plusDays(daysUntilSaturday);
    }

    private LocalDate parseNextMonthDate(String text) {
        if (!NEXT_MONTH_PATTERN.matcher(text).find())
            return null;

        LocalDate now = LocalDate.now().plusMonths(1);
        return LocalDate.of(now.getYear(), now.getMonth(), 1);
    }

    private ParsedTaskResponse buildTaskEntity(String prompt, String normalized) {
        ParsedTaskResponse task = new ParsedTaskResponse();
        task.setTitle(buildTitle(normalized));
        task.setDescription(prompt.trim());
        task.setPriority(parsePriority(normalized));
        task.setStatus(parseStatus(normalized));
        task.setCompleted(task.getStatus() == StatusEnum.COMPLETED);

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

        return entities;
    }

    private String extractTimeExpression(String text) {
        Matcher timeMatcher = TIME_PATTERN.matcher(text);

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

        if (WEEKEND_PATTERN.matcher(text).find())
            return "cuối tuần";

        if (NEXT_MONTH_PATTERN.matcher(text).find())
            return "tháng sau";

        if (Pattern.compile("\\b(ngày mai|mai)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return "ngày mai";

        if (Pattern.compile("\\b(hôm nay)\\b", Pattern.CASE_INSENSITIVE).matcher(text).find())
            return "hôm nay";

        return null;
    }

    private String buildTitle(String text) {
        // Remove time/date/priority/status keywords first
        String cleaned = TITLE_REMOVE_PATTERN.matcher(text).replaceAll("")
                .replaceAll("\\s+", " ")
                .trim();

        // Remove leading intent keywords
        cleaned = cleaned.replaceAll("^(thêm|tạo|lập|nhắc|ghi|ghi lại|cần làm|tôi|mình|phải|cần|cho|và|thì|là)\\s+",
                "");
        cleaned = cleaned.replaceAll("\\s+(thêm|tạo|lập|nhắc|ghi|ghi lại|cần làm|tôi|mình|phải|cần|cho|và|thì|là)$",
                "");

        // Remove date patterns
        cleaned = TITLE_DATE_PATTERN.matcher(cleaned).replaceAll("")
                .replaceAll("\\s+", " ")
                .trim();

        // Remove time patterns
        cleaned = TITLE_TIME_PATTERN.matcher(cleaned).replaceAll("")
                .replaceAll("\\s+", " ")
                .trim();

        // Clean up leading/trailing filler words
        cleaned = cleaned.replaceAll("^(cho|và|thì|là|ở|tại|vào|lúc|để|với|của|những|các|có|việc|phải|đi|ra|ngoài)\\s+",
                "");
        cleaned = cleaned.replaceAll("\\s+(cho|và|thì|là|ở|tại|vào|lúc|để|với|của|những|các)$", "");
        cleaned = cleaned.trim();

        // Take first meaningful segment (before comma or period)
        String candidate = cleaned.split("[,\\.]")[0].trim();

        // Validate candidate: should be meaningful (3+ chars) and not just time/date
        // words
        if (candidate.length() >= 3 && !isOnlyTimeDateWords(candidate)) {
            return candidate;
        }

        // Fallback: try different extraction approach
        String fallback = extractActionPhrase(text);
        if (!fallback.isEmpty() && !isOnlyTimeDateWords(fallback)) {
            return fallback;
        }

        // Final fallback
        return fallback.isEmpty() ? "Nhiệm vụ AI" : fallback;
    }

    private boolean isOnlyTimeDateWords(String text) {
        String normalized = text.toLowerCase().trim();
        String[] timeDateWords = {
                "thứ", "hai", "ba", "tư", "năm", "sáu", "bảy",
                "tuần", "sau", "tới", "nay", "mai", "qua",
                "sáng", "chiều", "tối", "trưa", "đêm",
                "ngày", "tháng", "năm", "giờ", "phút", "giây",
                "cuối", "tuan"
        };
        for (String word : timeDateWords) {
            normalized = normalized.replaceAll("\\b" + word + "\\b", "").replaceAll("\\s+", " ").trim();
        }
        return normalized.isEmpty();
    }

    private String extractActionPhrase(String text) {
        String cleaned = text.toLowerCase();

        // Remove intent keywords from start
        cleaned = cleaned.replaceAll(
                "^(thêm|tạo|lập|nhắc|ghi|ghi lại|thêm mới|tạo mới|lập mới|cần làm|cho tôi|giúp tôi)\\s+", "");

        // Remove time expressions
        cleaned = cleaned.replaceAll(
                "\\b(thứ\\s*(?:hai|ba|tư|năm|sáu|bảy|[2-7])|chủ nhật|cn)\\b", "");
        cleaned = cleaned.replaceAll(
                "\\b(ngày\\s*\\d{1,2}\\s*(?:tháng|thang)\\s*\\d{1,2}|\\d{1,2}\\s*(?:tháng|thang)\\s*\\d{1,2}|ngày\\s*\\d{1,2})\\b",
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

        // Clean up
        cleaned = cleaned.replaceAll(
                "^(cho|và|thì|là|ở|tại|vào|lúc|để|với|của|những|các|có|việc|phải|đi|ra|ngoài|mất|tiêu)\\s+", "");
        cleaned = cleaned.replaceAll(
                "\\s+(cho|và|thì|là|ở|tại|vào|lúc|để|với|của|những|các)$", "");
        cleaned = cleaned.replaceAll("\\s+", " ").trim();

        // Split by comma/period and take first meaningful part
        String[] parts = cleaned.split("[,\\.]");
        for (String part : parts) {
            String trimmed = part.trim();
            if (trimmed.length() >= 3 && !isOnlyTimeDateWords(trimmed)) {
                return trimmed;
            }
        }

        return cleaned;
    }

    private String normalizeText(String text) {
        return text == null ? "" : text.trim().replaceAll("\\s+", " ").replaceAll("[“”‘’]", "\"").toLowerCase();
    }
}
