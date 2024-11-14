package com.sparta.project.dto.order;

import com.sparta.project.domain.Order;

import java.time.LocalDateTime;

public record OrderResponse(
        Long userId,
        String orderId,
        String type,
        String status,
        int orderPrice,
        LocalDateTime createdAt,
        LocalDateTime deletedAt
) {

    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getUser().getUserId(),
                order.getOrderId(),
                order.getType().name(),
                order.getStatus().name(),
                order.getOrderPrice(),
                order.getCreatedAt(),
                order.getDeletedAt()
        );
    }
}
