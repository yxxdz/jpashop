package jpabook.jpashop.admin.controller;

import jpabook.jpashop.admin.service.AdmMemberService;
import jpabook.jpashop.common.domain.Address;
import jpabook.jpashop.common.domain.Member;
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
import java.util.List;

import static jpabook.jpashop.common.domain.Role.USER;

@Controller
@RequiredArgsConstructor
public class AdmMemberController {

    private final AdmMemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/admin/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "admin/members/memberList";
    }

}
