package jpabook.jpashop.user.service;

import jpabook.jpashop.common.domain.*;
import jpabook.jpashop.common.domain.item.Item;
import jpabook.jpashop.user.repository.UsrItemRepository;
import jpabook.jpashop.user.repository.UsrMemberRepository;
import jpabook.jpashop.user.repository.UsrOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsrOrderService {

    private final UsrOrderRepository orderRepository;
    private final UsrMemberRepository memberRepository;
    private final UsrItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    // 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }

    // 개인 주문 목록
    public List<Order> findOrders(Long memberId) {
        return orderRepository.findOrders(memberId);
    }

}
