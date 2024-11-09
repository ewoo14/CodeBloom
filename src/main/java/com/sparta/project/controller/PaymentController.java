//package com.sparta.project.controller;
//
//import com.sparta.project.dto.payment.PaymentRequest;
//import com.sparta.project.dto.payment.PaymentResponse;
//import com.sparta.project.dto.common.ApiResponse;
//import com.sparta.project.dto.common.PageResponse;
//import com.sparta.project.service.PaymentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/payments")
//public class PaymentController {
//
//    private final PaymentService paymentService;
//
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
//    // 결제 요청(CUSTOMER)
//    @PostMapping
//    public ApiResponse<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
//        PaymentResponse requestedPayment = paymentService.createPayment(paymentRequest);
//        return ApiResponse.success(requestedPayment);
//    }
//
//    // 결제 취소(CUSTOMER)
//    @DeleteMapping("/{payment_id}")
//    public ApiResponse<Void> deletePayment(@PathVariable String payment_id) {
//        paymentService.deletePayment(payment_id);
//        return ApiResponse.success();
//    }
//}
