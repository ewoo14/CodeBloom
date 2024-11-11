package com.sparta.project.domain.enums;

import com.sparta.project.exception.CodeBloomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PgNameTest {

    @ParameterizedTest
    @DisplayName("유효한 PG사 이름 문자열로 PgName을 생성한다.")
    @ValueSource(strings = {"NHN", "KG", "TOSS"})
    void testValidPgNameCreation(String name) {
        // given & when
        PgName pgName = PgName.of(name);

        // then
        assertThat(pgName.name()).isEqualTo(name);
    }

    @Test
    @DisplayName("유효하지 않은 PG사 이름 문자열로 PgName 생성 시 예외가 발생한다.")
    void testInvalidPgNameCreation() {
        // given
        String invalidName = "INVALID";

        // when & then
        assertThatThrownBy(() -> PgName.of(invalidName))
                .isInstanceOf(CodeBloomException.class);
    }
}