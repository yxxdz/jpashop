package jpabook.jpashop.admin.controller;

import jpabook.jpashop.admin.service.AdmCategoryService;
import jpabook.jpashop.common.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdmCategoryController {

    private final AdmCategoryService categoryService;

    @RequestMapping("/admin/categories")
    public String list(Model model) {
        List<Category> categories = categoryService.findCategories();
        model.addAttribute("categories", categories);
        return "admin/category/admCategoryList";
    }
}
