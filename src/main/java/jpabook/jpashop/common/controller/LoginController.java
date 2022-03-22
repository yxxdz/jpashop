package jpabook.jpashop.common.controller;

import jpabook.jpashop.common.domain.Address;
import jpabook.jpashop.common.domain.Member;
import jpabook.jpashop.user.controller.MemberForm;
import jpabook.jpashop.user.service.UsrMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static jpabook.jpashop.common.domain.Role.USER;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UsrMemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage(Model model, Authentication authentication,
                            @RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/login";
    }
}
