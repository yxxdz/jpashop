package jpabook.jpashop.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class UsrHomeController {

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        log.info("home controller");
        return "user/usrHome";
    }
}
