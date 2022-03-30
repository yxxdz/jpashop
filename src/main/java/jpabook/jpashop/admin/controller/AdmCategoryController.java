package jpabook.jpashop.admin.controller;

import jpabook.jpashop.admin.service.AdmCategoryService;
import jpabook.jpashop.common.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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

    @GetMapping("/admin/categories/new")
    public String createForm(Model model) {

        List<Category> categories = categoryService.findCategories();
        model.addAttribute("categories", categories);
        return "admin/category/createCategoryForm";
    }

    @PostMapping("/admin/categories/new")
    public String createForm(CategoryForm form) {
        Category category = new Category();
        category.setName(form.getName());
        category.setKoName(form.getKoName());

        if (form.getParentId() != null) {
            Category parent = new Category();
            parent.setId(form.getParentId());
            category.setParent(parent);
        }
        categoryService.saveCategories(category);

        return "redirect:/admin/categories";
    }
}
