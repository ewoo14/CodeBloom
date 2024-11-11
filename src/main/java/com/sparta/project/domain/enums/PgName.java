package com.sparta.project.domain.enums;

<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
=======
>>>>>>> b1c6552 ([Feat] Payment에 PG사 이름, PG사 결제 코드 추가)
=======
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
>>>>>>> edbb19b ([Refactor] PgName, PaymentType of 메서드 만들어서 유효성검증과 생성 한꺼번에)
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PgName {
<<<<<<< HEAD
<<<<<<< HEAD
    NHN("NHN KCP", "kcp"),
    KG("KG이니시스", "html5_inicis"),
    TOSS("토스페이먼츠", "tosspayments");

    private final String description;
    private final String pgKey;

    public static PgName of(String request) {
        return switch (request) {
            case "NHN" -> NHN;
            case "KG" -> KG;
            case "TOSS" -> TOSS;
            default -> throw new CodeBloomException(ErrorCode.UNSUPPORTED_PG_NAME);
        };
    }
=======
    NHN("NHN KCP"),
    KG("KG이니시스"),
    TOSS("토스페이먼츠");

    private final String description;
>>>>>>> b1c6552 ([Feat] Payment에 PG사 이름, PG사 결제 코드 추가)
=======
    NHN("NHN KCP", "kcp"),
    KG("KG이니시스", "html5_inicis"),
    TOSS("토스페이먼츠", "tosspayments");

    private final String description;
    private final String pgKey;

    public static PgName of(String request) {
        return switch (request) {
            case "NHN" -> NHN;
            case "KG" -> KG;
            case "TOSS" -> TOSS;
            default -> throw new CodeBloomException(ErrorCode.UNSUPPORTED_PG_NAME);
        };
    }
>>>>>>> 054108d (결제 기능 구현 Service)
}
