package com.sparta.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // API CALL FAILED
    API_CALL_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "API 호출이 실패했습니다."),

    // Util
    ALREADY_PROCESSED(HttpStatus.CONFLICT, "이미 처리된 요청입니다."),

    // UNAUTHORIZED & FORBIDDEN
    FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "접근 권한이 존재하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호 정보가 일치하지 않습니다."),

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 유저 정보가 존재하지 않습니다."),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "유저 이름이 이미 존재합니다."),

    // Address
    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 배송지 정보가 존재하지 않습니다."),
    EXCEED_MAXIMUM_ADDRESS(HttpStatus.CONFLICT, "등록할 수 있는 최대 배송지 수를 초과했습니다."),

    // Store-request
    STORE_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 가게 요청 정보가 존재하지 않습니다."),

    // Store-category
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 카테고리 정보가 존재하지 않습니다."),
    CATEGORY_ALREADY_EXIST(HttpStatus.CONFLICT, "카테고리가 이미 존재합니다."),

    // Store
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 가게 정보가 존재하지 않습니다."),

    // Order
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 주문 정보가 존재하지 않습니다."),
    USER_ORDER_MISMATCH(HttpStatus.FORBIDDEN, "주문자 정보가 결제자와 일치하지 않습니다."),
    UNSUPPORTED_ORDER_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 주문 방식입니다."),

    // Review
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 리뷰 정보가 존재하지 않습니다."),

    // Menu
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 메뉴 정보가 존재하지 않습니다."),
    MENU_ALREADY_EXIST(HttpStatus.CONFLICT, "메뉴가 이미 존재합니다."),

    // AI
    AIDESCRIPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 AI설명 정보가 존재하지 않습니다."),
    RESPONSE_PARSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "응답 파싱 오류가 발생했습니다."),

    // Location
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 지역 정보가 존재하지 않습니다."),
    LOCATION_ALREADY_EXIST(HttpStatus.CONFLICT, "지역이 이미 존재합니다."),

    // Payment
    UNSUPPORTED_PAYMENT_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 결제 방식입니다."),
    UNSUPPORTED_PG_NAME(HttpStatus.BAD_REQUEST, "지원하지 않는 PG사입니다."),
    PAYMENT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "결제가 실패하였습니다." ),
    PAYMENT_CANCELLATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "결제 취소가 실패하였습니다."),
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 결제 정보가 존재하지 않습니다.")

    ;

    private final HttpStatus httpStatus;
    private final String message;

}
