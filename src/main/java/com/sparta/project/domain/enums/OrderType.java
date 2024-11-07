package com.sparta.project.domain.enums;

import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {
    ONLINE("온라인(비대면)"),
    OFFLINE("오프라인(대면)");

    private final String description;

    public static OrderType of(String request) {
        return switch (request) {
            case "ONLINE" -> ONLINE;
            case "OFFLINE" -> OFFLINE;
            default -> throw new CodeBloomException(ErrorCode.UNSUPPORTED_ORDER_TYPE);
        };
    }
}
