package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.exception.CustomException;
import jpabook.jpashop.exception.ErrorCode;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static jpabook.jpashop.exception.ErrorCode.VALIDATE_DUPLICATE_ID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;

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

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Override
    public UserDetails loadUserByUsername(String realId) throws UsernameNotFoundException {

        return accountRepository.findByRealId(realId)
                .orElseThrow(() -> new UsernameNotFoundException("일치하는 회원 정보가 없습니다."));
    }

    public Member findOneById(String realId) {return memberRepository.findOneById(realId);}
}
