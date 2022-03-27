package jpabook.jpashop.admin.controller;

import jpabook.jpashop.admin.service.AdmItemService;
import jpabook.jpashop.common.domain.Category;
import jpabook.jpashop.common.domain.item.Album;
import jpabook.jpashop.common.domain.item.Book;
import jpabook.jpashop.common.domain.item.Item;
import jpabook.jpashop.common.domain.item.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdmItemController {

    private final AdmItemService itemService;

    @GetMapping("/admin/items/types")
    public String selectDType(Model model) {
        return "admin/items/selectDType";
    }

    @GetMapping("/admin/items/new")
    public String createBookForm(HttpServletRequest request, Model model) {

        String dtype = request.getParameter("dtype");

        model.addAttribute("dtype", dtype);
        model.addAttribute("form", new ItemForm());
        return "admin/items/createItemForm";
    }

    @PostMapping("/admin/items/new")
    public String createBook(HttpServletRequest request, ItemForm form) {

        String dtype = request.getParameter("dtype");

        if (dtype.equals("A")) {
            Album album = new Album();
            album.setName(form.getName());
            album.setPrice(form.getPrice());
            album.setStockQuantity(form.getStockQuantity());
            album.setArtist(form.getArtist());
            album.setEtc(form.getEtc());

            itemService.saveItem(album);

        } else if (dtype.equals("B")) {
            Book book = new Book();
            book.setName(form.getName());
            book.setPrice(form.getPrice());
            book.setStockQuantity(form.getStockQuantity());
            book.setAuthor(form.getAuthor());
            book.setIsbn(form.getIsbn());

            itemService.saveItem(book);

        } else if (dtype.equals("M")) {
            Movie movie = new Movie();
            movie.setName(form.getName());
            movie.setPrice(form.getPrice());
            movie.setStockQuantity(form.getStockQuantity());
            movie.setDirector(form.getDirector());
            movie.setActor(form.getActor());

            itemService.saveItem(movie);
        }

        return "redirect:/admin/items";
    }

    @GetMapping("/admin/items")
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
        return "admin/items/admItemList";
    }

    @GetMapping("/admin/items/{dtype}/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId,
                                 @PathVariable("dtype")String dtype,
                                 Model model) {

        if (dtype.equals("A")) {
            Album item = (Album) itemService.findOne(itemId);

            ItemForm form = new ItemForm();
            form.setId(item.getId());
            form.setName(item.getName());
            form.setPrice(item.getPrice());
            form.setStockQuantity(item.getStockQuantity());
            form.setArtist(item.getArtist());
            form.setEtc(item.getEtc());

            model.addAttribute("form", form);

        } else if (dtype.equals("B")) {
            Book item = (Book) itemService.findOne(itemId);

            ItemForm form = new ItemForm();
            form.setId(item.getId());
            form.setName(item.getName());
            form.setPrice(item.getPrice());
            form.setStockQuantity(item.getStockQuantity());
            form.setAuthor(item.getAuthor());
            form.setIsbn(item.getIsbn());

            model.addAttribute("form", form);

        } else if (dtype.equals("M")) {
            Movie item = (Movie) itemService.findOne(itemId);

            ItemForm form = new ItemForm();
            form.setId(item.getId());
            form.setName(item.getName());
            form.setPrice(item.getPrice());
            form.setStockQuantity(item.getStockQuantity());
            form.setDirector(item.getDirector());
            form.setActor(item.getActor());

            model.addAttribute("form", form);

        }

        return "admin/items/updateItemForm";
    }

    @PostMapping("/admin/items/{dtype}/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId,
                             @PathVariable("dtype")String dtype,
                             @ModelAttribute("form") ItemForm form) {

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());

        return "redirect:/admin/items";
    }
}
