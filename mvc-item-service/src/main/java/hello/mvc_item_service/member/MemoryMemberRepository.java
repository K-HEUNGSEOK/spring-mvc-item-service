package hello.mvc_item_service.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static final Map<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return findByAll().stream()
                .filter(member -> member.getLoginName().equals(loginId))
                .findFirst();
    }

    @Override
    public List<Member> findByAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clear() {
        store.clear();
    }
}
