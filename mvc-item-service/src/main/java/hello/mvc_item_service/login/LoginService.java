package hello.mvc_item_service.login;

import hello.mvc_item_service.member.Member;
import hello.mvc_item_service.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member checkLoginMember(String loginId, String password){
      return memberRepository.findByLoginId(loginId)
               .stream()
               .filter(member -> member.getPassword().equals(password))
               .findFirst().orElse(null);
    }
}
