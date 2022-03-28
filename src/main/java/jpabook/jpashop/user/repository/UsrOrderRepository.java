package jpabook.jpashop.user.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.admin.repository.OrderSearch;
import jpabook.jpashop.common.domain.*;
import jpabook.jpashop.common.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UsrOrderRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public void save(Order order) { em.persist(order); }

    public Order findOne(Long id) { return em.find(Order.class, id); }

    // 개인 주문 목록
    public List<Order> findOrders(Long memberId, OrderSearch orderSearch) {
        QOrder order = QOrder.order;

        BooleanBuilder builder = new BooleanBuilder();

        if(orderSearch.getOrderStatus() != null) {
            builder.and(order.status.eq(orderSearch.getOrderStatus()));
        }

        return queryFactory.selectFrom(order)
                .where(order.member.id.eq(memberId).and(builder))
                .fetch();
    }
}
