package com.sparta.project.domain.enums;

<<<<<<< HEAD
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
=======
>>>>>>> af1c170 ([Refactor] Enum 타입에 description 추가)
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {
    ONLINE("온라인(비대면)"),
    OFFLINE("오프라인(대면)");

    private final String description;
<<<<<<< HEAD

    public static OrderType of(String request) {
        return switch (request) {
            case "ONLINE" -> ONLINE;
            case "OFFLINE" -> OFFLINE;
            default -> throw new CodeBloomException(ErrorCode.UNSUPPORTED_ORDER_TYPE);
        };
    }
=======
>>>>>>> af1c170 ([Refactor] Enum 타입에 description 추가)
}
