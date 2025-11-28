package hello.mvc_item_service.login;

import hello.mvc_item_service.login.dto.LoginForm;
import hello.mvc_item_service.member.Member;
import hello.mvc_item_service.util.SessionLoginId;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping
    public String login(Model model) {
        model.addAttribute("login", new LoginForm());
        return "login/loginForm";
    }

    @PostMapping
    public String loginForm(@Validated @ModelAttribute("login") LoginForm form,
                            BindingResult bindingResult,
                            @RequestParam(defaultValue = "/") String redirectURL,
                            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member member = loginService.checkLoginMember(form.getLoginName(), form.getPassword());
        if (member == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        //성공 로직
        HttpSession session = request.getSession();
        session.setAttribute(SessionLoginId.LOGIN_MEMBER, member);
        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
