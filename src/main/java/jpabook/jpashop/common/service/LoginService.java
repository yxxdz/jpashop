package jpabook.jpashop.common.service;

import jpabook.jpashop.common.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String realId) throws UsernameNotFoundException {

        return loginRepository.findByRealId(realId)
                .orElseThrow(() -> new UsernameNotFoundException("일치하는 회원 정보가 없습니다."));
    }
}
