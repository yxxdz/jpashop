package jpabook.jpashop.config;

import jpabook.jpashop.domain.Role;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberService memberService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String realId = request.getParameter("realId");

        HttpSession session = (HttpSession) request.getSession();
        session.setAttribute("loginMember", memberService.findOneById(realId));

        if (authentication.getAuthorities().toString().contains(Role.ADMIN.getKey())) {
            session.setAttribute("auth", Role.ADMIN.getKey());
            setDefaultTargetUrl("/admin");
        } else {
            session.setAttribute("auth", Role.USER.getKey());
            setDefaultTargetUrl("/");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
