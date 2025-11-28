package hello.mvc_item_service.web.interceptor;

import hello.mvc_item_service.util.SessionLoginId;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionLoginId.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 로그인 시도 = {}", requestURI);
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }
}
