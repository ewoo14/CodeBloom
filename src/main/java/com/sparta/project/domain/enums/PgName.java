package com.sparta.project.domain.enums;

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

    public static boolean isPgNameSupported(String pgName) {
        for (PgName pg : PgName.values()) {
            if (pg.name().equalsIgnoreCase(pgName)) {
                return true;
            }
        }
        return false;
    }
}
