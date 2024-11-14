package com.sparta.project.dto.order;

import com.sparta.project.domain.Order;

import java.time.LocalDateTime;

public record OrderResponse(
<<<<<<< HEAD
=======
        Long userId,
>>>>>>> 23b4e98 ([Feat] 주문 상세 조회)
        String orderId,
        String type,
        String status,
        int orderPrice,
        LocalDateTime createdAt,
<<<<<<< HEAD
        LocalDateTime rejectedAt
=======
        LocalDateTime deletedAt
>>>>>>> 23b4e98 ([Feat] 주문 상세 조회)
) {

    public static OrderResponse from(Order order) {
        return new OrderResponse(
<<<<<<< HEAD
=======
                order.getUser().getUserId(),
>>>>>>> 23b4e98 ([Feat] 주문 상세 조회)
                order.getOrderId(),
                order.getType().name(),
                order.getStatus().name(),
                order.getOrderPrice(),
                order.getCreatedAt(),
                order.getDeletedAt()
        );
    }
}
