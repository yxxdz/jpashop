package jpabook.jpashop.admin.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.common.domain.Category;
import jpabook.jpashop.common.domain.QCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdmCategoryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Category> findAll() {
        QCategory category = QCategory.category;

        return queryFactory.selectFrom(category)
                .fetch();
    }
}
