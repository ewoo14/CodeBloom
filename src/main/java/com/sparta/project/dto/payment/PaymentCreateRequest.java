package com.sparta.project.dto.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymentCreateRequest(
        @NotBlank String orderId,
        @NotBlank String type,
        @NotNull @Positive Integer paymentPrice,
        @NotBlank String pgName
) {
}
