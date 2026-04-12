package todo.list.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInformationResponse {
    public Long id;
    public String email;
}
