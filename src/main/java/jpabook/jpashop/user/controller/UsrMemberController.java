package jpabook.jpashop.user.controller;

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
public class UsrMemberController {

    private final UsrMemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "user/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        String encodedPassword = passwordEncoder.encode(form.getPassword());

        if (result.hasErrors()) {
            return "user/members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setRealId(form.getRealId());
        member.setPassword(encodedPassword);
        member.setName(form.getName());
        member.setPhone(form.getPhone());
        member.setAddress(address);
        member.setRole(USER);

        memberService.join(member);
        return "redirect:/login";
    }
}
