package jpabook.jpashop.admin.service;

import jpabook.jpashop.admin.repository.AdmItemRepository;
import jpabook.jpashop.admin.repository.AdmMemberRepository;
import jpabook.jpashop.admin.repository.AdmOrderRepository;
import jpabook.jpashop.admin.repository.OrderSearch;
import jpabook.jpashop.common.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdmOrderService {

    private final AdmOrderRepository orderRepository;
    private final AdmMemberRepository memberRepository;
    private final AdmItemRepository itemRepository;

    // 검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByQuerydsl(orderSearch);
    }

    @Transactional
    public void changeDeliveryStatus(Long deliveryId, String status) {
        orderRepository.changeDeliveryStatus(deliveryId, status);
    }
}
