package jpabook.jpashop.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class AdmHomeController {

    @RequestMapping("/admin")
    public String adminPage() {
        return "admin/admHome";
    }
}
