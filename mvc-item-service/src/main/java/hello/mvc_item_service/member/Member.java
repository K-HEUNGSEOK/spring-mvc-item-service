package hello.mvc_item_service.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Member {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String loginName;
    @NotEmpty
    private String password;
}
