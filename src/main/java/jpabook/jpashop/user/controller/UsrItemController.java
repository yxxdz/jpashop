package jpabook.jpashop.user.controller;

import jpabook.jpashop.common.domain.item.Book;
import jpabook.jpashop.common.domain.item.Item;
import jpabook.jpashop.user.service.UsrItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsrItemController {

    private final UsrItemService itemService;

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "user/items/usrItemList";
    }
}
