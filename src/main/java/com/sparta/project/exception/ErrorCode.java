package com.sparta.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

<<<<<<< HEAD
<<<<<<< HEAD
    // API CALL FAILED
    API_CALL_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "API 호출이 실패했습니다."),

    // Util
    ALREADY_PROCESSED(HttpStatus.CONFLICT, "이미 처리된 요청입니다."),
    UNSUPPORTED_SORT_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 정렬 방식입니다."),

=======
>>>>>>> 4bb355b ([Feat] 로그인 API)
=======
    // API CALL FAILED
    API_CALL_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "API 호출이 실패했습니다."),

>>>>>>> fea02e7 ([Feat] AI Dto 및 service 구현)
    // UNAUTHORIZED & FORBIDDEN
    FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "접근 권한이 존재하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호 정보가 일치하지 않습니다."),

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 유저 정보가 존재하지 않습니다."),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "유저 이름이 이미 존재합니다."),
=======
    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 유저 정보가 존재하지 않습니다."),
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)

    // Address
    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 배송지 정보가 존재하지 않습니다."),
    EXCEED_MAXIMUM_ADDRESS(HttpStatus.CONFLICT, "등록할 수 있는 최대 배송지 수를 초과했습니다."),

<<<<<<< HEAD
    // Store-request
    STORE_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 가게 요청 정보가 존재하지 않습니다."),
    STORE_REQUEST_UNABLE_MODIFY(HttpStatus.BAD_REQUEST, "승인 대기 상태의 요청만 수정 가능합니다."),

=======
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
    // Store-category
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 카테고리 정보가 존재하지 않습니다."),
    CATEGORY_ALREADY_EXIST(HttpStatus.CONFLICT, "카테고리가 이미 존재합니다."),

    // Store
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 가게 정보가 존재하지 않습니다."),
<<<<<<< HEAD

    // Order
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 주문 정보가 존재하지 않습니다."),
    USER_ORDER_MISMATCH(HttpStatus.FORBIDDEN, "주문자 정보가 결제자와 일치하지 않습니다."),
    UNSUPPORTED_ORDER_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 주문 방식입니다."),
    CANNOT_APPROVE_ORDER(HttpStatus.BAD_REQUEST, "승인할 수 없는 주문 상태입니다."),
    CANNOT_CANCEL_ORDER(HttpStatus.BAD_REQUEST, "취소할 수 없는 주문 상태입니다."),
    ORDER_CANCELLATION_TIME_EXPIRED(HttpStatus.BAD_REQUEST, "주문한 지 5분이 지나면 취소할 수 없습니다."),

    // Review
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 리뷰 정보가 존재하지 않습니다."),

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
=======
    // NOT FOUND
<<<<<<< HEAD
<<<<<<< HEAD
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 유저 정보가 존재하지 않습니다.")
>>>>>>> 4bb355b ([Feat] 로그인 API)
=======
    // Location
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 지역 정보가 존재하지 않습니다."),

    // Store-category
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 카테고리 정보가 존재하지 않습니다."),

    // NOT FOUND
<<<<<<< HEAD
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 유저 정보가 존재하지 않습니다.");
>>>>>>> 1c2a362 ([Style] ErrorCode 도메인 별로 위치 분리)
=======
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 유저 정보가 존재하지 않습니다."),
<<<<<<< HEAD
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 메뉴 정보가 존재하지 않습니다.")
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
=======
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 유저 정보가 존재하지 않습니다."),
>>>>>>> 9863864 ([Fix] Menu Service 메서드별 테스트 완성)
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 메뉴 정보가 존재하지 않습니다."),
    AIDESCRIPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 AI설명 정보가 존재하지 않습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 가게 정보가 존재하지 않습니다."),

    // Response Parsing Error
    RESPONSE_PARSING_ERROR(HttpStatus.BAD_REQUEST, "응답 파싱 오류가 발생했습니다.")
>>>>>>> fea02e7 ([Feat] AI Dto 및 service 구현)

    ;

<<<<<<< HEAD
=======
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 유저 정보가 존재하지 않습니다."),
=======
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)

    // Order
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 주문 정보가 존재하지 않습니다."),
    USER_ORDER_MISMATCH(HttpStatus.FORBIDDEN, "주문자 정보가 결제자와 일치하지 않습니다."),
    UNSUPPORTED_ORDER_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 주문 방식입니다."),
    CANNOT_APPROVE_ORDER(HttpStatus.BAD_REQUEST, "승인할 수 없는 주문 상태입니다."),
    CANNOT_CANCEL_ORDER(HttpStatus.BAD_REQUEST, "취소할 수 없는 주문 상태입니다."),
    ORDER_CANCELLATION_TIME_EXPIRED(HttpStatus.BAD_REQUEST, "주문한 지 5분이 지나면 취소할 수 없습니다."),

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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    PAYMENT_FAILED(HttpStatus.BAD_REQUEST, "결제가 실패하였습니다." ),
=======
    PAYMENT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "결제가 실패하였습니다." )

    ;
>>>>>>> edbb19b ([Refactor] PgName, PaymentType of 메서드 만들어서 유효성검증과 생성 한꺼번에)
=======
    PAYMENT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "결제가 실패하였습니다." );

>>>>>>> 1bccd96 ([Feat] 주문자 정보와 결제자 정보가 일치 않으면 USER_ORDER_MISMATCH 예외 발생)
=======
    PAYMENT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "결제가 실패하였습니다."),

    // Store
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 가게 정보가 존재하지 않습니다."),
>>>>>>> 377f60d ([Build] ErrorCode 정돈, Location, StoreCategory, Store 의 id 자동 생성 되도록)

    // Store
=======
    PAYMENT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "결제가 실패하였습니다." ),
<<<<<<< HEAD
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 메뉴 정보가 존재하지 않습니다."),
    AIDESCRIPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 AI설명 정보가 존재하지 않습니다."),
>>>>>>> b66282a ([Minor] AI 설명생성 목록 조회 에러처리 코드 추가)
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 가게 정보가 존재하지 않습니다."),

<<<<<<< HEAD
    // Location
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 위치 정보가 존재하지 않습니다."),

    // StoreCategory
    STORE_CATEGROY_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 음식점 카테고리 정보가 존재하지 않습니다."),
=======
    // Response Parsing Error
    RESPONSE_PARSING_ERROR(HttpStatus.BAD_REQUEST, "응답 파싱 오류가 발생했습니다.");
>>>>>>> 377f60d ([Build] ErrorCode 정돈, Location, StoreCategory, Store 의 id 자동 생성 되도록)
=======
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
=======
    PAYMENT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "결제가 실패하였습니다."),
>>>>>>> 5c7a118 ([Feat] 주문 승인)

    ;

>>>>>>> 054108d (결제 기능 구현 Service)
=======
>>>>>>> 9863864 ([Fix] Menu Service 메서드별 테스트 완성)
    private final HttpStatus httpStatus;
    private final String message;

}
