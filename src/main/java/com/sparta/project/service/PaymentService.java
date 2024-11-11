package com.sparta.project.service;

import com.sparta.project.client.PgClient;
<<<<<<< HEAD
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
    public Page<PaymentResponse> getPaymentsByUser(Long userId, Pageable pageable) {
        QPayment qPayment = QPayment.payment;
        BooleanExpression predicate = qPayment.user.userId.eq(userId);
        return paymentRepository.findAll(predicate, pageable)
                .map(PaymentResponse::from);
    }

    // OWNER의 가게 결제 내역 조회
    @Transactional(readOnly = true)
    public Page<PaymentResponse> getMyStorePayments(Long userId, Pageable pageable) {
        Store store = storeRepository.findByOwnerUserId(userId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.STORE_NOT_FOUND));

        QPayment qPayment = QPayment.payment;
        BooleanExpression predicate = qPayment.order.store.storeId.eq(store.getStoreId());
        return paymentRepository.findAll(predicate, pageable)
                .map(PaymentResponse::from);
    }

    // 가게 결제 내역 목록 조회
    @Transactional(readOnly = true)
    public Page<PaymentResponse> getAllPayments(String storeId, Pageable pageable) {
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
=======
import com.sparta.project.domain.Order;
import com.sparta.project.domain.Payment;
import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.PaymentType;
import com.sparta.project.domain.enums.PgName;
import com.sparta.project.dto.payment.PaymentCreateResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentService {
    private final UserService userService;
    private final OrderService orderService;
    private final PaymentRepository paymentRepository;
    private final PgClient pgClient;

    @Transactional
    public PaymentCreateResponse createPayment(String orderId, String type, int paymentPrice, String pgName, Long userId) {
         User user = userService.getUserOrException(userId);
        Order order = orderService.getUserOrException(orderId);

        validateUserOrderMatch(order, userId);

        Payment payment = Payment.create(order, user, PaymentType.of(type), paymentPrice, PgName.of(pgName));

        boolean isSuccess = pgClient.requestPayment(payment);

        if (!isSuccess) {
            throw new CodeBloomException(ErrorCode.PAYMENT_FAILED);
        }

        return PaymentCreateResponse.from(paymentRepository.save(payment));
    }
<<<<<<< HEAD
<<<<<<< HEAD

    // todo paymetType, pgName 검사하는 클래스를 분리해서 단일 책임 원칙을 지키는 게 객체지향스러울 것 같다.
    private boolean isPaymentTypeNotSupported(String type) {
        return !PaymentType.isPaymentTypeSupported(type);
    }

    private boolean isPgNameNtoSupported(String pgName) {
        return !PgName.isPgNameSupported(pgName);
>>>>>>> 054108d (결제 기능 구현 Service)
    }
=======
>>>>>>> edbb19b ([Refactor] PgName, PaymentType of 메서드 만들어서 유효성검증과 생성 한꺼번에)
=======

    private void validateUserOrderMatch(Order order, Long userId) {
        if (!order.getUser().getUserId().equals(userId)) {
            throw new CodeBloomException(ErrorCode.USER_ORDER_MISMATCH);
        }
    }
>>>>>>> 1bccd96 ([Feat] 주문자 정보와 결제자 정보가 일치 않으면 USER_ORDER_MISMATCH 예외 발생)
}
