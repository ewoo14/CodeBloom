package com.sparta.project.domain.enums;

import com.sparta.project.exception.CodeBloomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentTypeTest {

    @ParameterizedTest
    @DisplayName("유효한 결제 타입 문자열로 PaymentType을 생성한다.")
    @ValueSource(strings = {"CARD", "CASH"})
    void testValidPaymentTypeCreation(String type) {
        // given & when
        PaymentType paymentType = PaymentType.of(type);

        // then
        assertThat(paymentType.name()).isEqualTo(type);
    }

    @Test
    @DisplayName("유효하지 않은 결제 타입 문자열로 PaymentType 생성 시 예외가 발생한다.")
    void testInvalidPaymentTypeCreation() {
        // given
        String invalidType = "INVALID";

        // when & then
        assertThatThrownBy(() -> PaymentType.of(invalidType))
                .isInstanceOf(CodeBloomException.class);
    }
}