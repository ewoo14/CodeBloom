package com.sparta.project.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {
    ONLINE("온라인(비대면)"),
    OFFLINE("오프라인(대면)");

    private final String description;
}
