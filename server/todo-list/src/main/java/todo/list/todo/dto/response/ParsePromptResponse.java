package todo.list.todo.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParsePromptResponse {
    private String intent;
    private ParsedTaskResponse task;
    private ParseEntitiesResponse entities;
    private String assistantMessage;
}
