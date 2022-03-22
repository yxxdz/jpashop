package jpabook.jpashop.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.common.domain.Member;
import jpabook.jpashop.common.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UsrMemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findById(String realId) {
        return em.createQuery("select m from Member m where m.realId = :realId", Member.class)
                .setParameter("realId", realId)
                .getResultList();
    }

    public Member findOneById(String realId) {
        QMember member = QMember.member;

        return queryFactory.selectFrom(member)
                .where(member.realId.eq(realId))
                .fetchOne();
    }
}
