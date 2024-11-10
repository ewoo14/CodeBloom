package com.sparta.project.dto.payment;

import com.sparta.project.domain.Payment;

public record PaymentCreateResponse(String paymentId) {

    public static PaymentCreateResponse from(Payment payment) {
        return new PaymentCreateResponse(payment.getPaymentId());
    }
}
