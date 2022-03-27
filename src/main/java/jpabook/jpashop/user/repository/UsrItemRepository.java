package jpabook.jpashop.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.common.domain.Category;
import jpabook.jpashop.common.domain.QCategory;
import jpabook.jpashop.common.domain.item.Item;
import jpabook.jpashop.common.domain.item.QItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UsrItemRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public List<Item> findAll(String category) {
        QItem item = QItem.item;

        return queryFactory.selectFrom(item)
                .where(item.dtype.eq(category))
                .fetch();
    }

    public List<Category> findCategories() {
        QCategory category = QCategory.category;

        return queryFactory.selectFrom(category)
//                .where(category.parent.id.isNull())
                .fetch();
    }
}
