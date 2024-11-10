package com.sparta.project.domain.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PgNameTest {

    @ParameterizedTest
    @DisplayName("pgName을 입력받으면 지원하는 pg사인 경우 true를 반환한다.")
    @ValueSource(strings = {"NHN", "KG", "TOSS"})
    void testSupportedPgNames(String pgName) {
        // given & when & then
        Assertions.assertThat(PgName.isPgNameSupported(pgName)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("지원하지 않는 pgName을 입력받으면 false를 반환한다.")
    @ValueSource(strings = {"PAYCO", "KAKAOPAY", "UNKNOWN"})
    void testUnsupportedPgNames(String pgName) {
        // given & when & then
        Assertions.assertThat(PgName.isPgNameSupported(pgName)).isFalse();
    }
}