package com.sparta.project.dto.payment;

import com.sparta.project.domain.Payment;
import com.sparta.project.domain.enums.PaymentType;

import java.time.LocalDateTime;

public record PaymentResponse (
        String paymentId,
        String orderId,
        PaymentType type,
        Integer paymentPrice,
        LocalDateTime createdAt
) {
    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getOrder().getOrderId(),
                payment.getType(),
                payment.getPaymentPrice(),
                payment.getCreatedAt()
        );
    }
}
