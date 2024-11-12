<<<<<<< HEAD:src/main/java/com/sparta/project/permission/PermissionValidator.java
package com.sparta.project.permission;
=======
package com.sparta.project.util;
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지):src/main/java/com/sparta/project/util/PermissionValidator.java

import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
