<<<<<<< HEAD
package com.sparta.project.controller;

<<<<<<< HEAD
<<<<<<< HEAD
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            }) Pageable pageable,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        Long userId = Long.parseLong(authentication.getName());
        Page<PaymentResponse> payments = paymentService.getPaymentsByUser(userId, pageable);
        return ApiResponse.success(PageResponse.of(payments));
    }

    // 자신의 가게 결제 내역 목록 조회(OWNER)
    @GetMapping("/myStore")
    public ApiResponse<PageResponse<PaymentResponse>> getMyStorePayments(
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            }) Pageable pageable,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
        Long userId = Long.parseLong(authentication.getName());
        Page<PaymentResponse> payments = paymentService.getMyStorePayments(userId, pageable);
        return ApiResponse.success(PageResponse.of(payments));
    }

    // 가게 결제 내역 목록 조회(MANAGER, MASTER)
    @GetMapping
    public ApiResponse<PageResponse<PaymentResponse>> getAllPayments(
            @RequestParam String storeId,
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            }) Pageable pageable,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        Page<PaymentResponse> payments = paymentService.getAllPayments(storeId, pageable);
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
=======
import com.sparta.project.dto.payment.PaymentRequest;
import com.sparta.project.dto.payment.PaymentResponse;
=======
import com.sparta.project.dto.PaymentRequest;
import com.sparta.project.dto.PaymentResponse;
>>>>>>> d0a8147 ([Fix] controller 내 dto 경로 수정)
import com.sparta.project.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    // 자신의 결제 내역 목록 조회
    @GetMapping("/my")
    public List<PaymentResponse> getMyPayments() {
        return paymentService.getMyPayments();
    }

    // 가게 결제 내역 목록 조회
    @GetMapping("/stores/{store_id}")
    public List<PaymentResponse> getPaymentsByStore(@PathVariable String store_id) {
        return paymentService.getPaymentsByStore(store_id);
    }

    // 결제 내역 상세 조회
    @PostMapping("/{payment_id}")
    public PaymentResponse updatePayment(@PathVariable String payment_id, @RequestBody PaymentRequest paymentRequest) {
        return paymentService.updatePayment(payment_id, paymentRequest);
    }

    // 결제 요청
    @PostMapping
    public PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.createPayment(paymentRequest);
    }

    // 결제 취소
    @DeleteMapping("/{payment_id}")
    public void deletePayment(@PathVariable String payment_id) {
        paymentService.deletePayment(payment_id);
>>>>>>> c220516 ([Feat] : 컨트롤러 초안 작성)
    }
}
=======
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
>>>>>>> 5f194e3 ([Fix] AI명세서대로 컨트롤러 초안 수정)
