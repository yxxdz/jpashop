package jpabook.jpashop.user.controller;

import jpabook.jpashop.admin.repository.OrderSearch;
import jpabook.jpashop.common.domain.Order;
import jpabook.jpashop.common.domain.item.Item;
import jpabook.jpashop.user.service.UsrItemService;
import jpabook.jpashop.user.service.UsrOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UsrOrderController {

    private final UsrOrderService orderService;
    private final UsrItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {

        List<Item> items = itemService.findItems();

        model.addAttribute("items", items);

        return "user/order/orderForm";
    }

    // 상품 목록에서 버튼 클릭 시 해당 상품 주문서 불러오기 추가
    @PostMapping("/order/{itemId}")
    public String createItemForm(Model model,
                             @PathVariable("itemId") Long itemId) {

        List<Item> items = itemService.findItems();

        model.addAttribute("items", items);
        model.addAttribute("itemId", itemId);

        return "user/order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count,
                        RedirectAttributes redirectAttributes) {

        orderService.order(memberId, itemId, count);

        String str = String.valueOf(memberId);
        redirectAttributes.addFlashAttribute("memberId", str);
        return "redirect:/orders";
    }

    // 회원 주문 목록
    @RequestMapping("/orders")
    public String redirectOrderList( @ModelAttribute("orderSearch") OrderSearch orderSearch,
                                     HttpServletRequest request,
                                     Model model) {

        String str = "";

        Map<String, ?> redirectMap = RequestContextUtils.getInputFlashMap(request);
        if (redirectMap != null) {
            str = (String) redirectMap.get("memberId");
        }

        Long memberId = Long.parseLong(str);

        List<Order> orders = orderService.findOrders(memberId, orderSearch);
        model.addAttribute("orders", orders);

        return "user/order/usrOrderList";
    }

    // 회원 주문 목록
    @PostMapping("/orders/{memberId}")
    public String orderList(@PathVariable("memberId") Long memberId,
                            @ModelAttribute("orderSearch") OrderSearch orderSearch,
                            Model model) {

        List<Order> orders = orderService.findOrders(memberId, orderSearch);
        model.addAttribute("orders", orders);

        return "user/order/usrOrderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId,
                              @RequestParam("memberId") Long memberId,
                              RedirectAttributes redirectAttributes) {

        orderService.cancelOrder(orderId);

        String str = String.valueOf(memberId);
        redirectAttributes.addFlashAttribute("memberId", str);
        return "redirect:/orders";
    }
}
