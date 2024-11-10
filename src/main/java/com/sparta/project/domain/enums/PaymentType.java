package com.sparta.project.domain.enums;

<<<<<<< HEAD
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
=======
>>>>>>> af1c170 ([Refactor] Enum 타입에 description 추가)
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {
    CARD("카드"),
    CASH("현금");

    private final String description;
<<<<<<< HEAD
<<<<<<< HEAD

    public static PaymentType of(String request) {
        return switch (request) {
            case "CARD" -> CARD;
            case "CASH" -> CASH;
            default -> throw new CodeBloomException(ErrorCode.UNSUPPORTED_PAYMENT_TYPE);
        };
    }
=======
>>>>>>> af1c170 ([Refactor] Enum 타입에 description 추가)
=======

    public static boolean isPaymentTypeSupported(String type) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.name().equals(type)) {
                return true;
            }
        }
        return false;
    }
>>>>>>> 054108d (결제 기능 구현 Service)
}
