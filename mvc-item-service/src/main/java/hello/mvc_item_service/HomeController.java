package hello.mvc_item_service;

import hello.mvc_item_service.web.argumentresolver.Login;
import hello.mvc_item_service.member.Member;
import hello.mvc_item_service.util.SessionLoginId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {
    //@GetMapping("/")
    public String home(@SessionAttribute(value = SessionLoginId.LOGIN_MEMBER, required = false) Member member,
                       Model model) {
        if (member == null) {
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeV2(@Login Member member, Model model) {
        if (member == null) {
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }
}
