package com.sparta.project.util;

import java.util.UUID;

public class UuidGenerator {
    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }

    // 정적 메서드만 사용할 거니까 생성 못하도록
    private UuidGenerator() {}
<<<<<<< HEAD
}
=======
}
>>>>>>> 054108d (결제 기능 구현 Service)
