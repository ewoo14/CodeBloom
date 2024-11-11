package com.sparta.project.controller;

import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.payment.PaymentCreateRequest;
import com.sparta.project.dto.payment.PaymentCreateResponse;
import com.sparta.project.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

//    // 자신의 결제 내역 목록 조회(CUSTOMER, OWNER)
//    @GetMapping("/my")
//    public ApiResponse<PageResponse<PaymentResponse>> getMyPayments(
//            @RequestParam Long userId,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<PaymentResponse> payments = paymentService.getPaymentsByUser(userId, page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(payments));
//    }
//
//    // 가게 결제 내역 목록 조회(MANAGER, MASTER)
//    @GetMapping
//    public ApiResponse<PageResponse<PaymentResponse>> getAllPayments(
//            @RequestParam String storeId,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<PaymentResponse> payments = paymentService.getAllPayments(storeId, page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(payments));
//    }
//
//    // 결제 내역 상세 조회(CUSTOMER)
//    @GetMapping("/{payment_id}")
//    public ApiResponse<PaymentResponse> getPaymentById(@PathVariable String payment_id) {
//        PaymentResponse payment = paymentService.getPaymentById(payment_id);
//        return ApiResponse.success(payment);
//    }
//
    // 결제 요청(CUSTOMER)
    @PostMapping
    public ApiResponse<PaymentCreateResponse> createPayment(@Valid @RequestBody PaymentCreateRequest paymentRequest,
                                                            Authentication authentication) {
        Long userId = Long.parseLong(authentication.getName());
        PaymentCreateResponse response
                = paymentService.createPayment(paymentRequest.orderId(),
                paymentRequest.type(), paymentRequest.paymentPrice(), paymentRequest.pgName(), userId);
        return ApiResponse.success(response);
    }
//
//    // 결제 취소(CUSTOMER)
//    @DeleteMapping("/{payment_id}")
//    public ApiResponse<Void> deletePayment(@PathVariable String payment_id) {
//        paymentService.deletePayment(payment_id);
//        return ApiResponse.success();
//    }
}
