package jpabook.jpashop.admin.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.common.domain.Category;
import jpabook.jpashop.common.domain.Order;
import jpabook.jpashop.common.domain.QCategory;
import jpabook.jpashop.common.domain.QOrder;
import jpabook.jpashop.common.domain.item.Item;
import jpabook.jpashop.common.domain.item.QItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdmItemRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
//        return em.createQuery("select i from Item i", Item.class)
//                .getResultList();

        QItem item = QItem.item;
        QCategory category = QCategory.category;
        QCategory categoryItem = new QCategory("category_item");

        return queryFactory.selectFrom(item)
                .fetch();
    }
}
