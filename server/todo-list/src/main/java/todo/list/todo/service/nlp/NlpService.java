package todo.list.todo.service.nlp;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import todo.list.todo.dto.response.ParsePromptResponse;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

@ApplicationScoped
public class NlpService {
    @Inject
    OnnxBertNlpParser onnxBertNlpParser;

    @Inject
    ModelBasedNlpParser modelBasedNlpParser;

    @Inject
    RuleBasedNlpParser ruleBasedNlpParser;

    public ParsePromptResponse parsePrompt(String prompt) {
        if (onnxBertNlpParser.isEnabled()) {
            System.out.println("[NLP] ONNX BERT NLP enabled, calling ONNX parser for prompt: " + prompt);
            try {
                return buildFriendlyResponse(onnxBertNlpParser.parsePrompt(prompt), prompt);
            } catch (Exception e) {
                System.out.println("[NLP] ONNX parser failed, falling back to model-based parsing: " + e.getMessage());
            }
        }

        if (modelBasedNlpParser.isEnabled()) {
            System.out.println("[NLP] Model-based NLP enabled, calling local model parser for prompt: " + prompt);
            try {
                return buildFriendlyResponse(modelBasedNlpParser.parsePrompt(prompt), prompt);
            } catch (Exception e) {
                System.out.println(
                        "[NLP] Model-based parser failed, falling back to rule-based parsing: " + e.getMessage());
            }
        }

        System.out.println("[NLP] Using rule-based parser for prompt: " + prompt);
        return buildFriendlyResponse(ruleBasedNlpParser.parsePrompt(prompt), prompt);
    }

    private ParsePromptResponse buildFriendlyResponse(ParsePromptResponse response, String prompt) {
        if (response == null)
            return null;

        if (response.getAssistantMessage() != null && !response.getAssistantMessage().isBlank())
            return response;

        if (!"CREATE".equalsIgnoreCase(response.getIntent()) || response.getTask() == null)
            return response;

        boolean hasDate = containsDateReference(prompt);
        boolean hasTime = containsTimeReference(prompt);

        if (!hasDate || !hasTime) {
            if (!hasDate && !hasTime) {
                response.setAssistantMessage(
                        "I need both date and time information to create the task properly. Please tell me the exact date and time 😅");
            } else if (!hasDate) {
                response.setAssistantMessage(
                        "I need a specific due date to create the task. Please tell me the date 😅");
            } else {
                response.setAssistantMessage(
                        "I need a specific due time to create the task. Please tell me the time 😅");
            }

            response.setTask(null);
            response.setEntities(null);
            return response;
        }

        if (!hasMeaningfulTaskDescription(prompt)) {
            response.setAssistantMessage(
                    "I need a clear task description to create something for you. Please tell me what you want to do, not just the date and time 😅");
            response.setTask(null);
            response.setEntities(null);

            return response;
        }

        String title = response.getTask().getTitle() != null ? response.getTask().getTitle() : "your task";
        String dueDateText = "no due date";
        if (response.getTask().getDueDate() != null)
            dueDateText = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.forLanguageTag("vi-VN"))
                    .withZone(ZoneId.systemDefault())
                    .format(response.getTask().getDueDate());

        response.setAssistantMessage(String.format(
                "I have created the task \"%s\" for you. The due date is %s. If you want me to create another task, just say so 🥰!",
                title, dueDateText));

        return response;
    }

    private boolean containsDateReference(String prompt) {
        if (prompt == null || prompt.isBlank())
            return false;

        String normalized = prompt.toLowerCase(Locale.forLanguageTag("vi-VN"));
        String datePattern = "\\b(nay|hôm\\s+nay|chiều\\s+nay|tối\\s+nay|sáng\\s+nay|trưa\\s+nay|đêm\\s+nay|hôm\\s+qua|hôm\\s+kia|hôm\\s+sau|mai|mốt|tháng\\s+này|cuối\\s+tháng\\s+này|cuối\\s+tuần\\s+này|cuối\\s+tuần|tháng\\s+sau|tuần\\s+sau|ngày\\s+\\d{1,2}|\\d{1,2}/\\d{1,2}|\\d{1,2}-\\d{1,2}|thứ\\s*[2-7]|thứ\\s+hai|chủ\\s+nhật|ngày\\s+kia|ngày\\s+sau|trong\\s+tuần)\\b";
        return Pattern.compile(datePattern, Pattern.CASE_INSENSITIVE).matcher(normalized).find();
    }

    private boolean containsTimeReference(String prompt) {
        if (prompt == null || prompt.isBlank())
            return false;

        String normalized = prompt.toLowerCase(Locale.forLanguageTag("vi-VN"));
        String timePattern = "\\b(\\d{1,2}\\s*(?:h|giờ|:)(?:\\d{1,2})?|sáng|chiều|tối|đêm|trưa|nửa\\s*đêm|nửa\\s*ngày)\\b";
        return Pattern.compile(timePattern, Pattern.CASE_INSENSITIVE).matcher(normalized).find();
    }

    private boolean hasMeaningfulTaskDescription(String prompt) {
        if (prompt == null || prompt.isBlank())
            return false;

        String normalized = prompt.toLowerCase(Locale.forLanguageTag("vi-VN"));
        String cleaned = normalized.replaceAll(
                "\\b(hôm\\s+nay|mai|mốt|ngày\\s+\\d{1,2}|\\d{1,2}\\s*(?:tháng|thang)\\s*\\d{1,2}|tháng\\s+\\d{1,2}|\\d{1,2}/\\d{1,2}|\\d{1,2}-\\d{1,2}|thứ\\s*[2-7]|thứ\\s+hai|chủ\\s+nhật|cuối\\s+tuần|tuần\\s+sau|tháng\\s+sau|ngày\\s+kia|ngày\\s+sau|trong\\s+tuần|cuối\\s+tuần|\\d{1,2}\\s*(?:h|giờ|:)(?:\\d{1,2})?|sáng|chiều|tối|đêm|trưa|nửa\\s*đêm|nửa\\s*ngày|lúc|vào|hãy|giúp( mình)?|tạo( giúp)?|lập|đặt|nhắc|tôi|mình|mua|đặt|hoàn thành|xong|đã xong|đang làm|gấp|khẩn|ưu tiên cao|thấp|bình thường|deadline|gửi sếp|gửi cho sếp|gửi sếp trước|phải nộp|phải xong|phải|cần xong|cần|báo cáo quan trọng|có|việc|phải|đi|ra|ngoài|mất|tiêu)\\b",
                "").replaceAll("[^\\p{L}\\p{Nd}]+", " ").trim();

        return cleaned.length() >= 3;
    }
}
