package com.sparta.project.controller;

import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.payment.PaymentRequest;
import com.sparta.project.dto.payment.PaymentResponse;
import com.sparta.project.permission.PermissionValidator;
import com.sparta.project.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final PermissionValidator permissionValidator;

    // 자신의 결제 내역 목록 조회(CUSTOMER)
    @GetMapping("/my")
    public ApiResponse<PageResponse<PaymentResponse>> getMyPayments(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        Long userId = Long.parseLong(authentication.getName());
        Page<PaymentResponse> payments = paymentService.getPaymentsByUser(userId, page, size);
        return ApiResponse.success(PageResponse.of(payments));
    }

    // 자신의 가게 결제 내역 목록 조회(OWNER)
    @GetMapping("/myStore")
    public ApiResponse<PageResponse<PaymentResponse>> getMyStorePayments(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
        Long userId = Long.parseLong(authentication.getName());
        Page<PaymentResponse> payments = paymentService.getMyStorePayments(userId, page, size);
        return ApiResponse.success(PageResponse.of(payments));
    }

    // 가게 결제 내역 목록 조회(MANAGER, MASTER)
    @GetMapping
    public ApiResponse<PageResponse<PaymentResponse>> getAllPayments(
            @RequestParam String storeId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "createdAt") String sortBy,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        Page<PaymentResponse> payments = paymentService.getAllPayments(storeId, page, size, sortBy);
        return ApiResponse.success(PageResponse.of(payments));
    }

    // 결제 내역 상세 조회(CUSTOMER)
    @GetMapping("/{payment_id}")
    public ApiResponse<PaymentResponse> getPaymentById(
            @PathVariable String payment_id,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        PaymentResponse payment = paymentService.getPaymentById(payment_id);
        return ApiResponse.success(payment);
    }

    // 결제 요청(CUSTOMER)
    @PostMapping
    public ApiResponse<PaymentResponse> createPayment(
            @Valid @RequestBody PaymentRequest paymentRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        Long userId = Long.parseLong(authentication.getName());
        PaymentResponse response = paymentService.createPayment(paymentRequest, userId);
        return ApiResponse.success(response);
    }

    // 결제 취소(CUSTOMER)
    @DeleteMapping("/{payment_id}")
    public ApiResponse<Void> deletePayment(
            @PathVariable String payment_id,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        paymentService.deletePayment(payment_id, authentication);
        return ApiResponse.success();
    }
}
