package com.sparta.project.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreRequestStatus {
    WAITING("승인대기"),
    APPROVE("승인"),
    REJECT("거부");

    private final String description;
}
