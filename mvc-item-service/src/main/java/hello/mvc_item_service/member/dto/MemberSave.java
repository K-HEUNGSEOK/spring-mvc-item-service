package hello.mvc_item_service.member.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberSave {
    @NotEmpty
    private String name;
    @NotEmpty
    private String loginName;
    @NotEmpty
    private String password;

}
