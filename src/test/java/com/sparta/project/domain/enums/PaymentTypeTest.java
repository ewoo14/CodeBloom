package com.sparta.project.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentTypeTest {

    @ParameterizedTest
    @DisplayName("유효한 결제 타입인지 확인한다.")
    @ValueSource(strings = {"CARD", "CASH"})
    void testSupportedPaymentTypes(String type) {
        // given & when & then
        assertTrue(PaymentType.isPaymentTypeSupported(type));
    }

    @ParameterizedTest
    @DisplayName("유효하지 않은 결제 타입이면 false를 반환한다.")
    @ValueSource(strings = {"PAYPAL", "BITCOIN", "UNKNOWN"})
    void testUnsupportedPaymentTypes(String type) {
        // given & when & then
        assertFalse(PaymentType.isPaymentTypeSupported(type));
    }
}