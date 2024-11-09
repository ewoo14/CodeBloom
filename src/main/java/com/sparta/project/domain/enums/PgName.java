package com.sparta.project.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PgName {
    NHN("NHN KCP"),
    KG("KG이니시스"),
    TOSS("토스페이먼츠");

    private final String description;
}
