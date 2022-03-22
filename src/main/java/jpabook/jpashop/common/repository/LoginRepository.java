package jpabook.jpashop.common.repository;

import jpabook.jpashop.common.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByRealId(String realId);
}
