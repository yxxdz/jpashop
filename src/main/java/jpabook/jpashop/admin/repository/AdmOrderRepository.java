package jpabook.jpashop.admin.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.common.domain.DeliveryStatus;
import jpabook.jpashop.common.domain.Order;
import jpabook.jpashop.common.domain.QDelivery;
import jpabook.jpashop.common.domain.QOrder;
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
public class AdmOrderRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public Order findOne(Long id) { return em.find(Order.class, id); }

    /**
     * JPQL (실무에서 못 씀 -> queryDSL)
     */
    public List<Order> findAllByString(OrderSearch orderSearch) {

        String jpql = "select o From Order o join o.member m";
        boolean isFirstCondition = true;

        // 주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        // 회원 이름 검색
        if(StringUtils.hasText(orderSearch.getMemberId())) {
            if(isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000);

        if(orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }

        if(StringUtils.hasText(orderSearch.getMemberId())) {
            query = query.setParameter("name", orderSearch.getMemberId());
        }

        return query.getResultList();
    }

    /**
     * JPA Criteria (실무에서 못 씀 -> queryDSL)
     */
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o =cq.from(Order.class);
        Join<Object, Object> m = o.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        // 주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberId())) {
           Predicate name =
                    cb.like(m.<String>get("name"), "%" + orderSearch.getMemberId() + "%");
           criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
        return query.getResultList();
    }

    /**
     * querydsl 추가
     */
    public List<Order> findAllByQuerydsl(OrderSearch orderSearch) {
        QOrder order = QOrder.order;

        BooleanBuilder builder = new BooleanBuilder();

        if(orderSearch.getOrderStatus() != null) {
            builder.and(order.status.eq(orderSearch.getOrderStatus()));
        }

        if(StringUtils.hasText(orderSearch.getMemberId())) {
            builder.and(order.member.realId.contains(orderSearch.getMemberId()));
        }

        return queryFactory.selectFrom(order)
                .where(builder)
                .fetch();
    }

    public void changeDeliveryStatus(Long deliveryId, String status) {
        QDelivery delivery = QDelivery.delivery;

        if (status.equals("READY")) {
            queryFactory.update(delivery)
                    .set(delivery.status, DeliveryStatus.READY)
                    .where(delivery.id.eq(deliveryId))
                    .execute();

        } else if (status.equals("COMP")) {
            queryFactory.update(delivery)
                    .set(delivery.status, DeliveryStatus.COMP)
                    .where(delivery.id.eq(deliveryId))
                    .execute();

        }


    }
}
