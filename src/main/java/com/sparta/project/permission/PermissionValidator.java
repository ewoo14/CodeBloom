<<<<<<< HEAD:src/main/java/com/sparta/project/permission/PermissionValidator.java
package com.sparta.project.permission;
=======
package com.sparta.project.util;
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지):src/main/java/com/sparta/project/util/PermissionValidator.java

import com.sparta.project.domain.Order;
import com.sparta.project.domain.Store;
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
<<<<<<< HEAD:src/main/java/com/sparta/project/permission/PermissionValidator.java
=======


    public void checkOrderApprovePermission(Order order, Long userId) {
        if (!isStoreOwner(order.getStore(), userId)) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }

    public void checkOrderCancelPermission(Order order, Long userId) {
        if (!isOrderCustomer(order, userId) && !isStoreOwner(order.getStore(), userId)) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }

    public boolean isStoreOwner(Store store, Long userId) {
        return userId.equals(store.getOwner().getUserId());
    }

    public boolean isOrderCustomer(Order order, Long userId) {
        return userId.equals(order.getUser().getUserId());
    }

>>>>>>> 2bce782 ([Fix] 주문 승인의 경우 OWNER 만 가능하므로, CUSTOMER, 다른 가게 주인은 승인하지 못하도록 수정):src/main/java/com/sparta/project/util/PermissionValidator.java
}
