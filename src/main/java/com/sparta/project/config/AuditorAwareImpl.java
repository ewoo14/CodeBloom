package com.sparta.project.config;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
=======
import com.sparta.project.config.jwt.UserDetail;
>>>>>>> 279b3b6 (AuditorAware 적용)
=======
>>>>>>> 980f191 ([Fix] AuditorAwareImpl<String> -> AuditorAwareImpl<Long> 으로 수정)
=======
import lombok.extern.slf4j.Slf4j;
>>>>>>> 69cad86 ([Fix] AuditorAwareImpl 변경 - 회원가입시 created_by와 updated_by는 "anonymous" 가 들어가도록 수정하여 회원가입이 되도록)
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
@Slf4j
=======
>>>>>>> 279b3b6 (AuditorAware 적용)
=======
@Slf4j
>>>>>>> 69cad86 ([Fix] AuditorAwareImpl 변경 - 회원가입시 created_by와 updated_by는 "anonymous" 가 들어가도록 수정하여 회원가입이 되도록)
public class AuditorAwareImpl implements AuditorAware<String> {
=======
public class AuditorAwareImpl implements AuditorAware<Long> {
>>>>>>> 980f191 ([Fix] AuditorAwareImpl<String> -> AuditorAwareImpl<Long> 으로 수정)
=======
public class AuditorAwareImpl implements AuditorAware<String> {
>>>>>>> 7a7528d ([Fix] AuditorAware 진짜 완료)

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

<<<<<<< HEAD
<<<<<<< HEAD
        if (null == authentication || !authentication.isAuthenticated()) {
=======
        if(null == authentication || !authentication.isAuthenticated()) {
>>>>>>> 279b3b6 (AuditorAware 적용)
=======
        if (null == authentication || !authentication.isAuthenticated()) {
>>>>>>> 69cad86 ([Fix] AuditorAwareImpl 변경 - 회원가입시 created_by와 updated_by는 "anonymous" 가 들어가도록 수정하여 회원가입이 되도록)
            return null;
        }

        //사용자 환경에 맞게 로그인한 사용자의 정보를 불러온다.
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 69cad86 ([Fix] AuditorAwareImpl 변경 - 회원가입시 created_by와 updated_by는 "anonymous" 가 들어가도록 수정하여 회원가입이 되도록)
        log.info("authentication.getName(): {}", authentication.getName());
        if (authentication.getName().equals("anonymousUser")) {
            return Optional.of("anonymous");
        }

        return Optional.of(authentication.getName());
=======
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        return Optional.of(userDetail.userId());
>>>>>>> 279b3b6 (AuditorAware 적용)
=======

<<<<<<< HEAD
        return Optional.of(Long.parseLong(authentication.getName()));
>>>>>>> 980f191 ([Fix] AuditorAwareImpl<String> -> AuditorAwareImpl<Long> 으로 수정)
=======
        return Optional.of(authentication.getName());
>>>>>>> 7a7528d ([Fix] AuditorAware 진짜 완료)
    }

}
