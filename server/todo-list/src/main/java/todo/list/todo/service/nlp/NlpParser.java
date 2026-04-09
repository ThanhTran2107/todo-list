package todo.list.todo.service.nlp;

import todo.list.todo.dto.response.ParsePromptResponse;

public interface NlpParser {
    ParsePromptResponse parsePrompt(String prompt);
}
