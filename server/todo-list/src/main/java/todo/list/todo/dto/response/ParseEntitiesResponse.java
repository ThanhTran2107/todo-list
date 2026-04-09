package todo.list.todo.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParseEntitiesResponse {
    private String title;
    private String description;
    private String dueDate;
    private String priority;
    private String status;
    private Boolean completed;
    private String timeExpression;
    private List<String> namedEntities;
}
