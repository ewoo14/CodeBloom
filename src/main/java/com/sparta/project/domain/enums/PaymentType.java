package com.sparta.project.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {
    CARD("카드"),
    CASH("현금");

    private final String description;

    public static boolean isPaymentTypeSupported(String type) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.name().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
