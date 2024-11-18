package com.sparta.project.service;

import com.sparta.project.client.PgClient;
import com.sparta.project.domain.*;
import com.sparta.project.domain.enums.PaymentType;
import com.sparta.project.domain.enums.PgName;
import com.sparta.project.dto.payment.PaymentRequest;
import com.sparta.project.dto.payment.PaymentResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.PaymentRepository;
import com.sparta.project.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final UserService userService;
    private final OrderService orderService;
    private final PaymentRepository paymentRepository;
    private final PgClient pgClient;
    private final StoreRepository storeRepository;

    // 자신의 결제 내역 목록 조회
    @Transactional(readOnly = true)
    public Page<PaymentResponse> getPaymentsByUser(Long userId, int page, int size) {
        Sort sort = Sort.by(Sort.Order.desc("createdAt"), Sort.Order.desc("updatedAt"));
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        QPayment qPayment = QPayment.payment;
        BooleanExpression predicate = qPayment.user.userId.eq(userId);
        return paymentRepository.findAll(predicate, pageable)
                .map(PaymentResponse::from);
    }

    // OWNER의 가게 결제 내역 조회
    @Transactional(readOnly = true)
    public Page<PaymentResponse> getMyStorePayments(Long userId, int page, int size) {
        Store store = storeRepository.findByOwnerUserId(userId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.STORE_NOT_FOUND));

        Sort sort = Sort.by(Sort.Order.desc("createdAt"), Sort.Order.desc("updatedAt"));
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        QPayment qPayment = QPayment.payment;
        BooleanExpression predicate = qPayment.order.store.storeId.eq(store.getStoreId());
        return paymentRepository.findAll(predicate, pageable)
                .map(PaymentResponse::from);
    }

    // 가게 결제 내역 목록 조회
    @Transactional(readOnly = true)
    public Page<PaymentResponse> getAllPayments(String storeId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));
        QPayment qPayment = QPayment.payment;
        QOrder qOrder = QOrder.order;
        BooleanExpression predicate = qPayment.order.store.storeId.eq(storeId);
        return paymentRepository.findAll(predicate, pageable)
                .map(PaymentResponse::from);
    }

    // 결제 내역 상세 조회
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentById(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.PAYMENT_NOT_FOUND));
        return PaymentResponse.from(payment);
    }

    // 결제 요청
    @Transactional
    public PaymentResponse createPayment(PaymentRequest paymentRequest, Long userId) {
        User user = userService.getUserOrException(userId);
        Order order = orderService.getOrderOrException(paymentRequest.orderId());

        validateUserOrderMatch(order, userId);

        Payment payment = Payment.create(order, user, PaymentType.of(paymentRequest.type()),
                paymentRequest.paymentPrice(), PgName.of(paymentRequest.pgName()));

        // 외부 결제 시스템에서 결제 승인 요청
        if (!pgClient.requestPayment(payment)) {
            throw new CodeBloomException(ErrorCode.PAYMENT_FAILED);
        }

        return PaymentResponse.from(paymentRepository.save(payment));
    }

    // 결제 취소
    @Transactional
    public void deletePayment(String paymentId, Authentication authentication) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.PAYMENT_NOT_FOUND));

        // 결제 취소 요청자 검증 (승인 요청자와 같은지 확인)
        if (payment.getUser().getUserId() != Long.parseLong(authentication.getName())) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }

        // 외부 결제 시스템에서 결제 취소 요청
        if (!pgClient.cancelPayment(payment)) {
            throw new CodeBloomException(ErrorCode.PAYMENT_CANCELLATION_FAILED);
        }

        payment.deleteBase(authentication.getName());
    }

    // 주문 정보와 고객 일치 검증
    private void validateUserOrderMatch(Order order, Long userId) {
        if (!order.getUser().getUserId().equals(userId)) {
            throw new CodeBloomException(ErrorCode.USER_ORDER_MISMATCH);
        }
    }
}
