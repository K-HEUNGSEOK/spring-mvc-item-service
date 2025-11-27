package hello.mvc_item_service.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long memberId);
    List<Member> findByAll();
    void clear();

}
