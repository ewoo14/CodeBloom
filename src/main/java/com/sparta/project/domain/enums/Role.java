package com.sparta.project.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    CUSTOMER("고객"),
    OWNER("사장님"),
    MANAGER("관리자"),
    MASTER("마스터");

    private final String description;
}
