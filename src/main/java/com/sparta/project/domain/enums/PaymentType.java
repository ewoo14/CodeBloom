package com.sparta.project.domain.enums;

import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {
    CARD("카드"),
    CASH("현금");

    private final String description;

    public static PaymentType of(String request) {
        return switch (request) {
            case "CARD" -> CARD;
            case "CASH" -> CASH;
            default -> throw new CodeBloomException(ErrorCode.UNSUPPORTED_PAYMENT_TYPE);
        };
    }
}
