package com.sparta.project.util;


import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import java.util.Arrays;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class PermissionValidator {

    public void checkPermission(Authentication authentication, String... roles) {
        boolean hasPermission = Arrays.stream(roles)
                .anyMatch(role -> authentication.getAuthorities()
                                .contains(new SimpleGrantedAuthority(role)));
        if (!hasPermission) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }

}
