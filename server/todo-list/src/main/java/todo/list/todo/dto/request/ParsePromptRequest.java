package todo.list.todo.dto.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParsePromptRequest {
    @NotBlank
    @Schema(description = "User prompt in Vietnamese", examples = "Thêm họp nhóm lúc 9h sáng mai, ưu tiên cao")
    private String prompt;
}
