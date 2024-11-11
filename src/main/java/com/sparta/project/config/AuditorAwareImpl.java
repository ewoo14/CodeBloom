package com.sparta.project.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(null == authentication || !authentication.isAuthenticated()) {
            return null;
        }

        //사용자 환경에 맞게 로그인한 사용자의 정보를 불러온다.

        return Optional.of(authentication.getName());
    }

}
