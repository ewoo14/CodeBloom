package com.sparta.project.domain.enums;

import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PgName {
    NHN("NHN KCP", "kcp"),
    KG("KG이니시스", "html5_inicis"),
    TOSS("토스페이먼츠", "tosspayments");

    private final String description;
    private final String pgKey;

    public static PgName of(String request) {
        return switch (request) {
            case "NHN" -> NHN;
            case "KG" -> KG;
            case "TOSS" -> TOSS;
            default -> throw new CodeBloomException(ErrorCode.UNSUPPORTED_PG_NAME);
        };
    }
}
