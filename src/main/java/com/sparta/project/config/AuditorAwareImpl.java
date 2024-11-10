package com.sparta.project.config;

<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
=======
import com.sparta.project.config.jwt.UserDetail;
>>>>>>> 279b3b6 (AuditorAware 적용)
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

<<<<<<< HEAD
@Slf4j
=======
>>>>>>> 279b3b6 (AuditorAware 적용)
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

<<<<<<< HEAD
        if (null == authentication || !authentication.isAuthenticated()) {
=======
        if(null == authentication || !authentication.isAuthenticated()) {
>>>>>>> 279b3b6 (AuditorAware 적용)
            return null;
        }

        //사용자 환경에 맞게 로그인한 사용자의 정보를 불러온다.
<<<<<<< HEAD
        log.info("authentication.getName(): {}", authentication.getName());
        if (authentication.getName().equals("anonymousUser")) {
            return Optional.of("anonymous");
        }

        return Optional.of(authentication.getName());
=======
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        return Optional.of(userDetail.userId());
>>>>>>> 279b3b6 (AuditorAware 적용)
    }

}
