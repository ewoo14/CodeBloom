package com.sparta.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // API CALL FAILED
    API_CALL_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "API 호출이 실패했습니다."),

    // UNAUTHORIZED & FORBIDDEN
    FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "접근 권한이 존재하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호 정보가 일치하지 않습니다."),

    // Location
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 지역 정보가 존재하지 않습니다."),

    // Store-category
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 카테고리 정보가 존재하지 않습니다."),

    // NOT FOUND
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 유저 정보가 존재하지 않습니다.");
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 메뉴 정보가 존재하지 않습니다."),
    AIDESCRIPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 AI설명 정보가 존재하지 않습니다."),

    // Response Parsing Error
    RESPONSE_PARSING_ERROR(HttpStatus.BAD_REQUEST, "응답 파싱 오류가 발생했습니다.")

    ;
    private final HttpStatus httpStatus;
    private final String message;

}
