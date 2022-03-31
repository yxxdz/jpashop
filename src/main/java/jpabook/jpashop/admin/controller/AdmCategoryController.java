package jpabook.jpashop.admin.controller;

import jpabook.jpashop.admin.service.AdmCategoryService;
import jpabook.jpashop.common.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
        model.addAttribute("form", new CategoryForm());
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

    @GetMapping("/admin/categories/{categoryId}/edit")
    public String updateCategoryForm(@PathVariable("categoryId") Long categoryId,
                                 Model model) {

        Category category = categoryService.findOne(categoryId);

        CategoryForm form = new CategoryForm();
        form.setId(category.getId());
        form.setName(category.getName());
        form.setKoName(category.getKoName());

        if (Objects.isNull(category.getParent())) {
            form.setParentId(null);
        } else {
            form.setParentId(category.getParent().getId());
        }

        List<Category> categories = categoryService.findCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("form", form);
        return "admin/category/updateCategoryForm";
    }

    @PostMapping("/admin/categories/{categoryId}/edit")
    public String updateCategory(@PathVariable Long categoryId,
                             @ModelAttribute("form") CategoryForm form) {

        categoryService.updateCategory(categoryId, form.getName(), form.getKoName(), form.getParentId());

        return "redirect:/admin/categories";
    }
}
