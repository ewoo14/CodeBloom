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
    public PaymentCreateResponse createPayment(String orderId, String type, int paymentPrice, String pgName, Long userId) {
         User user = userRepository.findById(userId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.USER_NOT_FOUND));
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.ORDER_NOT_FOUND));

        validateUserOrderMatch(order, userId);

        Payment payment = Payment.create(order, user, PaymentType.of(type), paymentPrice, PgName.of(pgName));

        boolean isSuccess = pgClient.requestPayment(payment);

        if (!isSuccess) {
            throw new CodeBloomException(ErrorCode.PAYMENT_FAILED);
        }

        return PaymentCreateResponse.from(paymentRepository.save(payment));
    }

    private void validateUserOrderMatch(Order order, Long userId) {
        if (!order.getUser().getUserId().equals(userId)) {
            throw new CodeBloomException(ErrorCode.USER_ORDER_MISMATCH);
        }
    }
}
