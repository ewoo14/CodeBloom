package com.sparta.project.util;

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

}
