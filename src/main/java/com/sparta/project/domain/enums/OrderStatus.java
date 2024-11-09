package com.sparta.project.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    WAITING("대기"),
    APPROVED("승인"),
    CANCELED("취소");

    private final String description;
}
