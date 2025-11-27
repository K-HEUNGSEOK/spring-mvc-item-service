package hello.mvc_item_service.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static final Map<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setMemberId(++sequence);
        store.put(member.getMemberId(), member);
        return member;
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
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
