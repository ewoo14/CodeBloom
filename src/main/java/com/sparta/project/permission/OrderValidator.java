package com.sparta.project.permission;

import com.sparta.project.domain.Order;
import com.sparta.project.domain.Store;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

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

    public void checkStoreOwnerPermission(Store store, Long userId) {
        if (!isStoreOwner(store, userId)) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }

    private boolean isStoreOwner(Store store, Long userId) {
        return userId.equals(store.getOwner().getUserId());
    }

    private boolean isOrderCustomer(Order order, Long userId) {
        return userId.equals(order.getUser().getUserId());
    }

    public void checkOrderOwner(Long userId, Long ownerId) {
        if (!userId.equals(ownerId)) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }
}
