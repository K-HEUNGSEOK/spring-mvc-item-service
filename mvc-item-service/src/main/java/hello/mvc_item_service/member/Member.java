package hello.mvc_item_service.member;

import lombok.Data;

@Data
public class Member {
    private Long memberId;
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public Member() {
    }
}
