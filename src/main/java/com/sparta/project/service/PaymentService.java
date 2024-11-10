package com.sparta.project.service;

import com.sparta.project.client.PgClient;
import com.sparta.project.domain.Order;
import com.sparta.project.domain.Payment;
import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.PaymentType;
import com.sparta.project.domain.enums.PgName;
import com.sparta.project.dto.payment.PaymentCreateResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.OrderRepository;
import com.sparta.project.repository.PaymentRepository;
import com.sparta.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PgClient pgClient;

    @Transactional
    public PaymentCreateResponse createPayment(String orderId, String type, int paymentPrice, String pgName, String username) {
         User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.USER_NOT_FOUND));
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.ORDER_NOT_FOUND));

        if (isPaymentTypeNotSupported(type)) {
            throw new CodeBloomException(ErrorCode.UNSUPPORTED_PAYMENT_TYPE);
        }

        if (isPgNameNtoSupported(pgName)) {
            throw new CodeBloomException(ErrorCode.UNSUPPORTED_PAYMENT_TYPE);
        }

        Payment payment = Payment.create(order, user, type, paymentPrice, PgName.valueOf(pgName));

        boolean isSuccess = pgClient.requestPayment(payment);

        if (!isSuccess) {
            throw new CodeBloomException(ErrorCode.PAYMENT_FAILED);
        }

        return PaymentCreateResponse.from(paymentRepository.save(payment));
    }

    // todo paymetType, pgName 검사하는 클래스를 분리해서 단일 책임 원칙을 지키는 게 객체지향스러울 것 같다.
    private boolean isPaymentTypeNotSupported(String type) {
        return !PaymentType.isPaymentTypeSupported(type);
    }

    private boolean isPgNameNtoSupported(String pgName) {
        return !PgName.isPgNameSupported(pgName);
    }
}
