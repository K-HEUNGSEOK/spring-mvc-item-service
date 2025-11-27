package hello.mvc_item_service.member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {
    MemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    void memberSuccessTest(){
        Member member = new Member("userA");
        Member saveMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getMemberId());

        assertThat(saveMember).isEqualTo(findMember);
    }
    @Test
    void memberFailTest(){
        Member findMember = memberRepository.findById(5L);
        assertThat(findMember).isNull();
    }

}