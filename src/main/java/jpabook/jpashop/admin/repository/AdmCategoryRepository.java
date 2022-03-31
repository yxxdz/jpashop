package jpabook.jpashop.admin.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.common.domain.Category;
import jpabook.jpashop.common.domain.QCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdmCategoryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public void save(Category category) {
        if (category.getId() == null) {
            em.persist(category);
        } else {
            em.merge(category);
        }
    }

    public List<Category> findAll() {
        QCategory category = QCategory.category;

        return queryFactory.selectFrom(category)
                .fetch();
    }

    public Category findOne(Long categoryId) {
        QCategory category = QCategory.category;

        return queryFactory.selectFrom(category)
                .where(category.id.eq(categoryId))
                .fetchOne();
    }
}
