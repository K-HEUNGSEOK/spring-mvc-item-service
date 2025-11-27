package hello.mvc_item_service.member;

import hello.mvc_item_service.member.dto.MemberSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository ;
    @GetMapping("/addMemberForm")
    public String member(Model model){
        Member member = new Member();
        model.addAttribute("member", member);
        return "member/addMemberForm";
    }
    @PostMapping("/addMemberForm")
    public String save(@Validated @ModelAttribute("member")MemberSave memberSave, BindingResult bindingResult ){
        if (bindingResult.hasErrors()){
            return "member/addMemberForm";
        }
        Member member = new Member(memberSave.getName(), memberSave.getLoginName(), memberSave.getPassword());
        memberRepository.save(member);
        return "redirect:/";
    }
}
