package hello.mvc_item_service.login.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
    @NotEmpty
    private String loginName;
    @NotEmpty
    private String password;
}
