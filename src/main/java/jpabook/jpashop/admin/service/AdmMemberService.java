package jpabook.jpashop.admin.service;

import jpabook.jpashop.admin.repository.AdmMemberRepository;
import jpabook.jpashop.common.domain.Member;
import jpabook.jpashop.common.exception.CustomException;
import jpabook.jpashop.common.repository.LoginRepository;
import jpabook.jpashop.user.repository.UsrMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static jpabook.jpashop.common.exception.ErrorCode.VALIDATE_DUPLICATE_ID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdmMemberService {

    private final AdmMemberRepository memberRepository;

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public Member findOneById(String realId) {return memberRepository.findOneById(realId);}
}
