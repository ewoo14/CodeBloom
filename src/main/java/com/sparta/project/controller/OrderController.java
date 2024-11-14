package com.sparta.project.controller;

import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.order.OrderCreateRequest;
import com.sparta.project.dto.order.OrderResponse;
import com.sparta.project.service.OrderService;
import com.sparta.project.util.PermissionValidator;
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
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final PermissionValidator permissionValidator;

    // 자신의 주문내역 목록 조회(CUSTOMER)
    @GetMapping("/my")
    public ApiResponse<PageResponse<OrderResponse>> getMyOrders(
            @RequestParam("storeId") String storeId,
            @PageableDefault(size = 5)
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        Page<OrderResponse> orders = orderService.getMyOrders(pageable, storeId);
        return ApiResponse.success(PageResponse.of(orders));
    }

    // 주문내역 상세 조회(CUSTOMER)
    @GetMapping("/{order_id}")
    public ApiResponse<OrderResponse> getOrderById(@PathVariable("order_id") String order_id,
                                                   Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        OrderResponse order = orderService.getOrderById(order_id, Long.parseLong(authentication.getName()));
        return ApiResponse.success(order);
    }


    // 고객의 주문내역 목록 조회(OWNER, MANAGER, MASTER)
    @GetMapping("/users")
    public ApiResponse<PageResponse<OrderResponse>> getAllOrdersByUser(
            @RequestParam("userId") Long userId,
            @PageableDefault(size = 5)
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name(), Role.MANAGER.name(), Role.MASTER.name());
        Page<OrderResponse> orders = orderService.getAllOrdersByUser(pageable, userId);
        return ApiResponse.success(PageResponse.of(orders));
    }

    // 주문 요청(CUSTOMER, OWNER)
    @PostMapping
    public ApiResponse<String> createOrder(@RequestBody @Valid OrderCreateRequest request,
                                           Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name(), Role.OWNER.name());
        return ApiResponse.success(orderService.createOrder(Long.parseLong(authentication.getName()), request));
    }

    // 주문 승인(OWNER)
    @PatchMapping("/{order_id}")
    public ApiResponse<String> updateOrderStatus(
            @PathVariable("order_id") String order_id,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
        String orderId = orderService.approveOrder(order_id, Long.parseLong(authentication.getName()));
        return ApiResponse.success(orderId);
    }

    // 주문 취소(CUSTOMER, OWNER)
    @DeleteMapping("/{order_id}")
    public ApiResponse<String> deleteOrder(@PathVariable("order_id") String order_id, Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name(), Role.OWNER.name());
        String orderId = orderService.cancelOrder(order_id, Long.parseLong(authentication.getName()));
        return ApiResponse.success(orderId);
    }
}
