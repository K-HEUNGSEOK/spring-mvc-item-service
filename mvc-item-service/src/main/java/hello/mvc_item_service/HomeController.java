package hello.mvc_item_service;

import hello.mvc_item_service.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(@SessionAttribute(value = SessionLoginId.LOGIN_MEMBER, required = false) Member member,
                       Model model) {
        if (member == null) {
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }
}
