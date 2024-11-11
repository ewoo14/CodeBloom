package com.sparta.project.client;

import com.sparta.project.domain.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class PgClient {

    /**
     * 외부 PG사 연동은 했다고 가정하고
     * 로그를 찍고 true를 반환하도록 함
     * */
    public boolean requestPayment(Payment payment) {
        log.info("Requesting payment for payment {}", payment);
        return true;
    }
}
