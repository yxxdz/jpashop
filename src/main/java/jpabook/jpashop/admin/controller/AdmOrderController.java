package jpabook.jpashop.admin.controller;

import jpabook.jpashop.admin.repository.OrderSearch;
import jpabook.jpashop.admin.service.AdmItemService;
import jpabook.jpashop.admin.service.AdmMemberService;
import jpabook.jpashop.admin.service.AdmOrderService;
import jpabook.jpashop.common.domain.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdmOrderController {

    private final AdmOrderService orderService;

    @GetMapping("/admin/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "admin/order/admOrderList";
    }

    @PostMapping("/admin/orders/delivery/{deliveryId}")
    public String changeDeliveryStatus(@PathVariable("deliveryId") Long deliveryId) {
        orderService.changeDeliveryStatus(deliveryId);
        return "redirect:/admin/orders";
    }
}
