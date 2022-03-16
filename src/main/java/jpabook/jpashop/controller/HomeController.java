package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        log.info("home controller");
        return "home";
    }

    @RequestMapping("/admin")
    public String adminPage() {
        return "/admin";
    }
}
