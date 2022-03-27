package jpabook.jpashop.user.controller;

import jpabook.jpashop.common.domain.Category;
import jpabook.jpashop.common.domain.item.Book;
import jpabook.jpashop.common.domain.item.Item;
import jpabook.jpashop.user.service.UsrItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsrItemController {

    private final UsrItemService itemService;

    @GetMapping("/items")
    public String list(@RequestParam(name = "category", required = false) String category,
                       Model model) {

        if (category != null && !category.equals("")) {
            List<Item> items = itemService.findItems(category);
            model.addAttribute("items", items);
            model.addAttribute("selectCategory", category);
        } else {
            List<Item> items = itemService.findItems();
            model.addAttribute("items", items);
        }


        List<Category> categories = itemService.findCategories();
        model.addAttribute("categories", categories);
        return "user/items/usrItemList";
    }
}
