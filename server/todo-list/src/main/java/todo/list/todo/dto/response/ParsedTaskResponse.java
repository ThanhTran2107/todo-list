package todo.list.todo.dto.response;

import lombok.Data;
import todo.list.todo.entity.enums.PriorityEnum;
import todo.list.todo.entity.enums.StatusEnum;

import java.time.Instant;

@Data
public class ParsedTaskResponse {
    private String title;
    private String description;
    private Instant dueDate;
    private PriorityEnum priority;
    private StatusEnum status;
    private Boolean completed;
}
