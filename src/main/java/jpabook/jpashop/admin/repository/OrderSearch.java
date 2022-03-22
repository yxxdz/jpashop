package jpabook.jpashop.admin.repository;

import jpabook.jpashop.common.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberId;  // 회원 이름
    private OrderStatus orderStatus;    //주문 상태[ORDER, CANCEL]
}
