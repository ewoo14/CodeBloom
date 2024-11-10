package com.sparta.project.domain.enums;

<<<<<<< HEAD
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
=======
>>>>>>> b1c6552 ([Feat] Payment에 PG사 이름, PG사 결제 코드 추가)
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

    public static boolean isPgNameSupported(String pgName) {
        for (PgName pg : PgName.values()) {
            if (pg.name().equalsIgnoreCase(pgName)) {
                return true;
            }
        }
        return false;
    }
>>>>>>> 054108d (결제 기능 구현 Service)
}
