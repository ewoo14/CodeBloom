<<<<<<< HEAD
<<<<<<< HEAD
package com.sparta.project.controller;

<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.order.OrderCreateRequest;
import com.sparta.project.dto.order.OrderResponse;
import com.sparta.project.permission.PermissionValidator;
import com.sparta.project.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
=======
package com.sparta.project.controller;

import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.order.OrderCreateRequest;
import com.sparta.project.service.OrderService;
import com.sparta.project.util.PermissionValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> c1fc115 ([Feat] 주문 요청)

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final PermissionValidator permissionValidator;

<<<<<<< HEAD
    // 자신의 주문내역 목록 조회(CUSTOMER)
    @GetMapping("/my")
    public ApiResponse<PageResponse<OrderResponse>> getMyOrders(
            @RequestParam(value = "storeId", required = false) String storeId,
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            Pageable pageable,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        Page<OrderResponse> orders = orderService.getMyOrders(pageable, storeId, Long.parseLong(authentication.getName()));
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


    // 고객의 주문내역 목록 조회(MANAGER, MASTER)
    @GetMapping("/users")
    public ApiResponse<PageResponse<OrderResponse>> getAllOrdersByUser(
            @RequestParam("userId") Long userId,
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            Pageable pageable,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        Page<OrderResponse> orders = orderService.getAllOrdersByUser(pageable, userId);
        return ApiResponse.success(PageResponse.of(orders));
    }

    // 가게의 주문내역 목록 조회(OWNER) - customerId 쿼리 파라미터로
    @GetMapping("/owners/stores/{storeId}")
    public ApiResponse<PageResponse<OrderResponse>> getStoreOrders(
            @PathVariable("storeId") String storeId,
            @RequestParam(value = "customerId", required = false) Long customerId,
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            Pageable pageable,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
        Page<OrderResponse> orders
                = orderService.getStoreOrders(pageable, storeId, customerId, Long.parseLong(authentication.getName()));
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
=======
import com.sparta.project.dto.order.OrderRequest;
import com.sparta.project.dto.order.OrderResponse;
=======
import com.sparta.project.dto.OrderRequest;
import com.sparta.project.dto.OrderResponse;
>>>>>>> d0a8147 ([Fix] controller 내 dto 경로 수정)
import com.sparta.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    // 자신의 주문내역 목록 조회
    @GetMapping("/my")
    public List<OrderResponse> getMyOrders(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortBy
    ) {
        return orderService.getMyOrders(page, size, sortBy);
    }

    // 주문내역 상세 조회
    @GetMapping("/{order_id}")
    public OrderResponse getOrderById(@PathVariable String order_id) {
        return orderService.getOrderById(order_id);
    }

    // 고객의 주문내역 목록 조회
    @GetMapping("/users/{user_id}")
    public List<OrderResponse> getOrdersByUser(@PathVariable String user_id) {
        return orderService.getOrdersByUser(user_id);
    }

    // 주문 요청
    @PostMapping
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    // 주문 승인
    @PostMapping("/{order_id}")
    public OrderResponse updateOrder(@PathVariable String order_id, @RequestBody OrderRequest orderRequest) {
        return orderService.updateOrder(order_id, orderRequest);
    }

    // 주문 취소
    @DeleteMapping("/{order_id}")
    public void deleteOrder(@PathVariable String order_id) {
        orderService.cancelOrder(order_id);
>>>>>>> c220516 ([Feat] : 컨트롤러 초안 작성)
    }
}
=======
//package com.sparta.project.controller;
//
//import com.sparta.project.dto.order.OrderRequest;
//import com.sparta.project.dto.order.OrderResponse;
//import com.sparta.project.dto.common.ApiResponse;
//import com.sparta.project.dto.common.PageResponse;
//import com.sparta.project.service.OrderService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/orders")
//public class OrderController {
//
//    private final OrderService orderService;
//
=======
>>>>>>> c1fc115 ([Feat] 주문 요청)
//    // 자신의 주문내역 목록 조회(CUSTOMER)
//    @GetMapping("/my")
//    public ApiResponse<PageResponse<OrderResponse>> getMyOrders(
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<OrderResponse> orders = orderService.getMyOrders(page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(orders));
//    }
//
//    // 주문내역 상세 조회(CUSTOMER)
//    @GetMapping("/{order_id}")
//    public ApiResponse<OrderResponse> getOrderById(@PathVariable String order_id) {
//        OrderResponse order = orderService.getOrderById(order_id);
//        return ApiResponse.success(order);
//    }
//
//    // 고객의 주문내역 목록 조회(OWNER, MANAGER, MASTER)
//    @GetMapping("/users")
//    public ApiResponse<PageResponse<OrderResponse>> getAllOrdersByUser(
//            @RequestParam Long userId,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<OrderResponse> orders = orderService.getAllOrdersByUser(userId, page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(orders));
//    }
//
    // 주문 요청(CUSTOMER, OWNER)
    @PostMapping
    public ApiResponse<String> createOrder(@RequestBody @Valid OrderCreateRequest request,
                                           Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name(), Role.OWNER.name());
        return ApiResponse.success(orderService.createOrder(Long.parseLong(authentication.getName()), request));
    }
//
//    // 주문 승인(OWNER)
//    @PatchMapping("/{order_id}")
//    public ApiResponse<OrderResponse> updateOrderStatus(
//            @PathVariable String order_id,
//            @RequestBody OrderRequest orderRequest) {
//        OrderResponse updatedOrder = orderService.updateOrderStatus(order_id, orderRequest);
//        return ApiResponse.success(updatedOrder);
//    }
//
//    // 주문 취소(CUSTOMER, OWNER)
//    @DeleteMapping("/{order_id}")
//    public ApiResponse<Void> deleteOrder(@PathVariable Long order_id) {
//        orderService.deleteOrder(order_id);
//        return ApiResponse.success();
//    }
<<<<<<< HEAD
//}
>>>>>>> 5f194e3 ([Fix] AI명세서대로 컨트롤러 초안 수정)
=======
}
>>>>>>> c1fc115 ([Feat] 주문 요청)
