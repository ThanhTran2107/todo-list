package todo.list.todo.service.nlp;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import todo.list.todo.dto.response.ParsePromptResponse;
import todo.list.todo.entity.enums.PriorityEnum;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class OnnxBertNlpParser implements NlpParser {
    private final OnnxBertIntentClassifier intentClassifier;
    private final OnnxBertSequenceLabeler sequenceLabeler;
    private final ModelBasedNlpParser fallbackParser;

    @Inject
    public OnnxBertNlpParser(ModelBasedNlpParser fallbackParser) {
        this.fallbackParser = fallbackParser;
        this.intentClassifier = new OnnxBertIntentClassifier();
        this.sequenceLabeler = new OnnxBertSequenceLabeler();
    }

    @Override
    public ParsePromptResponse parsePrompt(String prompt) {
        ParsePromptResponse response = fallbackParser.parsePrompt(prompt);
        if (!intentClassifier.isEnabled() && !sequenceLabeler.isEnabled()) {
            System.out.println("[NLP] ONNX BERT not enabled, using fallback parser.");
            return response;
        }

        String predictedIntent = intentClassifier.isEnabled() ? intentClassifier.predict(prompt) : response.getIntent();

        if (sequenceLabeler.isEnabled()) {
            Map<String, String> extracted = sequenceLabeler.predictEntities(prompt);
            System.out.println("[NLP] ONNX extracted entities: " + extracted);
            if (extracted.containsKey("TITLE") && isValidTitleOverride(extracted.get("TITLE"))) {
                response.getTask().setTitle(extracted.get("TITLE"));
                response.getEntities().setTitle(extracted.get("TITLE"));
            }

            String extractedTime = extracted.get("TIME");
            if (isValidExtractedValue(extractedTime))
                response.getEntities().setTimeExpression(extractedTime);

            String extractedDate = extracted.get("DATE");
            if (isValidDateOverride(extractedDate)) {
                Instant dueDateInstant = buildDueDateInstant(extractedDate, extractedTime,
                        response.getTask().getDueDate());

                if (dueDateInstant != null) {
                    response.getTask().setDueDate(dueDateInstant);
                    response.getEntities().setDueDate(dueDateInstant.toString());
                }
            }

            if (extracted.containsKey("PRIORITY") && isValidExtractedValue(extracted.get("PRIORITY"))) {
                String priorityValue = extracted.get("PRIORITY");
                response.getEntities().setPriority(priorityValue);
                PriorityEnum mappedPriority = mapPriority(priorityValue);

                if (mappedPriority != null)
                    response.getTask().setPriority(mappedPriority);
            }
        }

        System.out.println("[NLP] ONNX BERT parsed prompt: " + prompt);
        return new ParsePromptResponse(predictedIntent, response.getTask(), response.getEntities(), null);
    }

    public boolean isEnabled() {
        return intentClassifier.isEnabled() || sequenceLabeler.isEnabled();
    }

    private boolean isValidExtractedValue(String value) {
        if (value == null)
            return false;

        String normalized = value.trim();
        if (normalized.isEmpty())
            return false;

        if (normalized.equalsIgnoreCase("[UNK]") || normalized.equalsIgnoreCase("UNK"))
            return false;

        return normalized.length() >= 2;
    }

    private boolean isValidTitleOverride(String title) {
        if (!isValidExtractedValue(title))
            return false;

        String normalized = title.trim().toLowerCase();
        if (normalized.length() < 3)
            return false;

        // CRITICAL: Reject titles containing time patterns like "3h", "3 giờ", "15:00"
        // Example: "ngoài lúc 3h chiều" must be rejected because it contains "3h"
        if (normalized.matches(".*\\d{1,2}\\s*(?:h|giờ|:).*")) {
            System.out.println("[NLP] Rejecting title with time pattern: " + title);
            return false;
        }

        // Reject if title is ONLY time/date related words
        if (isOnlyTimeDateWords(normalized))
            return false;

        // Reject titles that are just common filler phrases
        String[] invalidPhrases = {
                "lúc", "giờ", "sáng", "chiều", "tối", "đêm", "ngày", "tháng", "năm",
                "cuối", "trước", "sau", "khi", "cho", "ở", "tại", "vào", "để", "với",
                "của", "những", "các", "và", "thì", "là", "có", "phải", "đi", "ra",
                "tôi có việc", "có việc", "phải đi", "tôi phải"
        };
        for (String bad : invalidPhrases) {
            if (normalized.equals(bad))
                return false;
        }

        // CRITICAL: Reject single common Vietnamese words that are likely NER errors
        // These are words that frequently appear in prompts but are not valid task
        // titles
        String[] commonWords = {
                "cáo", "báo", "án", "đồ", "việc", "làm", "đi", "ăn", "uống",
                "ngủ", "chơi", "học", "công", "buổi", "có", "tôi", "mình",
                "phải", "cần", "muốn", "sẽ", "đang", "đã", "sắp", "nên"
        };
        String[] words = normalized.split("\\s+");
        if (words.length == 1) {
            for (String common : commonWords) {
                if (words[0].equals(common)) {
                    System.out.println("[NLP] Rejecting single common word as title: " + title);
                    return false;
                }
            }
        }

        // Reject titles that are just common filler phrases
        String[] timeWords = { "lúc", "giờ", "sáng", "chiều", "tối", "đêm", "ngày", "tháng", "năm", "tuần" };
        String[] titleWords = normalized.split("\\s+");
        if (titleWords.length > 0) {
            for (String tw : timeWords) {
                if (titleWords[0].equals(tw) || titleWords[titleWords.length - 1].equals(tw)) {
                    System.out.println("[NLP] Rejecting title starting/ending with time word: " + title);
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isOnlyTimeDateWords(String text) {
        String normalized = text.toLowerCase().trim();
        String[] timeDateWords = {
                "thứ", "hai", "ba", "tư", "năm", "sáu", "bảy",
                "tuần", "sau", "tới", "nay", "mai", "qua",
                "sáng", "chiều", "tối", "trưa", "đêm",
                "ngày", "tháng", "năm", "giờ", "phút", "giây",
                "lúc", "vào", "khoảng", "đúng", "trước", "sau",
                "cuối", "tuan", "weekend"
        };
        for (String word : timeDateWords) {
            normalized = normalized.replaceAll("\\b" + word + "\\b", "").replaceAll("\\s+", " ").trim();
        }

        return normalized.isEmpty();
    }

    private boolean isValidDateOverride(String dateText) {
        if (!isValidExtractedValue(dateText))
            return false;

        String normalized = dateText.trim().toLowerCase();
        if (normalized.matches("^\\d{1,2}$"))
            return false;

        if (normalized.length() < 3)
            return false;

        return true;
    }

    private PriorityEnum mapPriority(String value) {
        if (value == null)
            return null;

        String normalized = value.trim().toUpperCase();
        return switch (normalized) {
            case "HIGH", "HIGHT", "CAO", "URGENT", "KHẨN", "GẤP" -> PriorityEnum.HIGH;
            case "LOW", "THẤP", "BÌNH THƯỜNG", "NORMAL" -> PriorityEnum.LOW;
            case "MEDIUM", "TRUNG BÌNH", "VỪA" -> PriorityEnum.MEDIUM;
            default -> null;
        };
    }

    private Instant buildDueDateInstant(String dateText, String timeText, Instant fallback) {
        if (dateText == null || dateText.isBlank())
            return fallback;

        LocalDate date = parseDateText(dateText);
        if (date == null)
            return fallback;

        LocalTime time = parseTimeText(timeText);
        if (time == null) {
            time = parseDefaultTime(dateText, fallback);
        }

        try {
            return LocalDateTime.of(date, time).atZone(ZoneId.systemDefault()).toInstant();
        } catch (DateTimeException e) {
            return fallback;
        }
    }

    private LocalTime parseDefaultTime(String text, Instant fallback) {
        String normalized = text == null ? "" : text.toLowerCase();
        if (normalized.contains("sáng"))
            return LocalTime.of(9, 0);
        if (normalized.contains("chiều"))
            return LocalTime.of(15, 0);
        if (normalized.contains("tối") || normalized.contains("đêm") || normalized.contains("toi"))
            return LocalTime.of(18, 0);
        if (normalized.contains("trưa"))
            return LocalTime.of(12, 0);
        if (fallback != null)
            return LocalDateTime.ofInstant(fallback, ZoneId.systemDefault()).toLocalTime();

        return LocalTime.of(18, 0);
    }

    private LocalDate parseDateText(String text) {
        if (text == null)
            return null;

        String normalized = text.toLowerCase().trim();
        LocalDate explicit = parseExplicitDate(normalized);
        if (explicit != null)
            return explicit;

        LocalDate weekday = parseWeekdayDate(normalized);
        if (weekday != null)
            return weekday;

        LocalDate weekend = parseWeekendDate(normalized);
        if (weekend != null)
            return weekend;

        LocalDate nextMonth = parseNextMonthDate(normalized);
        if (nextMonth != null)
            return nextMonth;

        LocalDate relative = parseRelativeDate(normalized);
        if (relative != null)
            return relative;

        return null;
    }

    private LocalDate parseExplicitDate(String text) {
        Matcher matcher = Pattern
                .compile("(?:ng\s*ay\s*)?(\\d{1,2})\\s*(?:thang|tháng)\\s*(\\d{1,2})(?:\\s*(?:nam|năm)\\s*(\\d{2,4}))?",
                        Pattern.CASE_INSENSITIVE)
                .matcher(text);
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

        matcher = Pattern.compile("(\\d{1,2})[\\/\\.-](\\d{1,2})(?:[\\/\\.-](\\d{2,4}))?", Pattern.CASE_INSENSITIVE)
                .matcher(text);
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

        return null;
    }

    private LocalDate parseRelativeDate(String text) {
        LocalDate now = LocalDate.now();
        if (text.contains("hôm nay") || text.contains("hom nay") || text.contains("hômnay") ||
                text.contains("chiều nay") || text.contains("tối nay") || text.contains("sáng nay") ||
                text.contains("trưa nay") || text.contains("đêm nay"))
            return now;
        if (text.contains("ngày mai") || text.matches(".*\\bmai\\b.*"))
            return now.plusDays(1);
        if (text.contains("mốt") || text.contains("mot") || text.contains("ngày kia"))
            return now.plusDays(2);
        if (text.contains("hôm qua") || text.contains("hom qua"))
            return now.minusDays(1);
        if (text.contains("tuần sau") && !text.contains("thứ")) {
            int currentDay = now.getDayOfWeek().getValue();
            int daysUntilMonday = (DayOfWeek.MONDAY.getValue() - currentDay + 7) % 7;
            if (daysUntilMonday == 0)
                daysUntilMonday = 7;

            return now.plusDays(daysUntilMonday);
        }

        if (text.contains("cuối tháng"))
            return now.withDayOfMonth(now.lengthOfMonth());

        if (text.contains("cuối năm"))
            return LocalDate.of(now.getYear(), 12, 31);

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
        if (!text.contains("cuối tuần") && !text.contains("cuoi tuan") && !text.contains("weekend"))
            return null;

        LocalDate now = LocalDate.now();
        int currentDay = now.getDayOfWeek().getValue();
        int daysUntilSaturday = (DayOfWeek.SATURDAY.getValue() - currentDay + 7) % 7;
        if (daysUntilSaturday == 0)
            daysUntilSaturday = 7;
        if (text.contains("tuần sau") || text.contains("tuan sau"))
            daysUntilSaturday += 7;

        return now.plusDays(daysUntilSaturday);
    }

    private LocalDate parseNextMonthDate(String text) {
        if (!text.contains("tháng sau"))
            return null;

        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        return LocalDate.of(nextMonth.getYear(), nextMonth.getMonth(), 1);
    }

    private LocalTime parseTimeText(String text) {
        if (text == null)
            return null;

        Matcher matcher = Pattern.compile("(\\d{1,2})(?:h| giờ| gio|:)(\\d{1,2})?", Pattern.CASE_INSENSITIVE)
                .matcher(text);
        if (!matcher.find())
            return null;

        int hour = Integer.parseInt(matcher.group(1));
        int minute = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 0;
        if (text.contains("chiều") || text.contains("toi") || text.contains("tối")) {
            if (hour < 12)
                hour += 12;
        }
        if (text.contains("sáng") && hour == 12)
            hour = 0;

        hour = Math.max(0, Math.min(hour, 23));
        minute = Math.max(0, Math.min(minute, 59));
        return LocalTime.of(hour, minute);
    }
}
