package todo.list.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class ResetPasswordRequest {
    @NotBlank
    @Schema(description = "Reset token")
    private String token;
    @NotBlank
    @Schema(description = "New password")
    private String newPassword;
}
