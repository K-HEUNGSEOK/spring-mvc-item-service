package hello.mvc_item_service.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Member {
    private Long id;
    private String name;
    private String loginName;
    private String password;

    public Member(String name, String loginName, String password) {
        this.name = name;
        this.loginName = loginName;
        this.password = password;
    }

    public Member() {
    }
}
