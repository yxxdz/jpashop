package jpabook.jpashop.user.service;

import jpabook.jpashop.common.domain.Member;
import jpabook.jpashop.common.exception.CustomException;
import jpabook.jpashop.user.repository.UsrMemberRepository;
import jpabook.jpashop.common.repository.LoginRepository;
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
public class UsrMemberService {

    private final UsrMemberRepository memberRepository;

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findById(member.getRealId());
        if(!findMembers.isEmpty()) {
            throw new CustomException(VALIDATE_DUPLICATE_ID);
        }
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public Member findOneById(String realId) {return memberRepository.findOneById(realId);}
}
