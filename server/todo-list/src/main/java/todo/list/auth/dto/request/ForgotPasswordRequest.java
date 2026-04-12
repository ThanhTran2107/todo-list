package todo.list.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class ForgotPasswordRequest {
    @Email
    @NotBlank
    @Schema(description = "Email")
    private String email;
}
