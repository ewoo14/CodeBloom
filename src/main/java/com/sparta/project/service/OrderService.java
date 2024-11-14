package com.sparta.project.service;

<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.domain.*;
import com.sparta.project.domain.enums.OrderType;
import com.sparta.project.dto.order.OrderCreateRequest;
import com.sparta.project.dto.order.OrderMenuInfo;
<<<<<<< HEAD
import com.sparta.project.dto.order.OrderResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.permission.OrderValidator;
import com.sparta.project.repository.OrderMenuRepository;
import com.sparta.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

=======
import com.sparta.project.domain.Order;
=======
import com.sparta.project.domain.*;
import com.sparta.project.domain.enums.OrderType;
import com.sparta.project.dto.order.OrderCreateRequest;
>>>>>>> c1fc115 ([Feat] 주문 요청)
=======
>>>>>>> 5f50f91 ([Refactor] 피드백 반영)
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.OrderMenuRepository;
import com.sparta.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
>>>>>>> 09c755a ([Refactor] OrderService 와 UserService의 getXxxOrException() 메서드 호출로 대체)
=======
import java.util.List;

>>>>>>> c1fc115 ([Feat] 주문 요청)
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> c1fc115 ([Feat] 주문 요청)
    private final UserService userService;
    private final AddressService addressService;
    private final MenuService menuService;
    private final StoreService storeService;
<<<<<<< HEAD
    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final OrderValidator orderValidator;

    @Transactional
    public String createOrder(Long userId, OrderCreateRequest request) {
        // 1. 사용자, 주소, 가게 정보 조회
        User user = userService.getUserOrException(userId);
        Address address = addressService.getAddressOrException(request.addressId());
        Store store = storeService.getStoreOrException(request.storeId());

        // 2. 주문 총 가격 계산
        int totalPrice = calculateTotalPrice(request.menus());

        // 3. Order 엔티티 생성
        Order order = Order.create(user, address, store, OrderType.of(request.type()), totalPrice, request.demand());
        orderRepository.save(order);

        // 4. OrderMenu 엔티티 생성 및 저장
        request.menus().forEach(menuInfo -> {
            Menu menu = menuService.getMenuOrException(menuInfo.menuId());
            OrderMenu orderMenu = OrderMenu.create(menu, order, menuInfo.count());
            orderMenuRepository.save(orderMenu);
            order.getOrderMenus().add(orderMenu);
        });

        return order.getOrderId();
    }

    private int calculateTotalPrice(List<OrderMenuInfo> menus) {
        return menus.stream()
                .mapToInt(menuInfo -> {
                    Menu menu = menuService.getMenuOrException(menuInfo.menuId());
                    return menu.getPrice() * menuInfo.count();
                })
                .sum();
    }

    @Transactional
    public String approveOrder(String orderId, Long userId) {
        Order order = getOrderOrException(orderId);
        orderValidator.checkOrderApprovePermission(order, userId);
        order.approve();
        return order.getOrderId();
    }

    // 주문 취소와 삭제는 같은 개념
    @Transactional
    public String cancelOrder(String orderId, Long userId) {
        Order order = getOrderOrException(orderId);
        orderValidator.checkOrderCancelPermission(order, userId);
        order.cancel(userId);
        return order.getOrderId();
    }

    public OrderResponse getOrderById(String orderId, long userId) {
        Order order = getOrderOrException(orderId);
        orderValidator.checkOrderOwner(userId, order.getUser().getUserId());
        return OrderResponse.from(order);
    }

    public Order getOrderOrException(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new CodeBloomException(ErrorCode.ORDER_NOT_FOUND));
    }

    public Page<OrderResponse> getMyOrders(Pageable pageable, String storeId, Long userId) {
        User user = userService.getUserOrException(userId); // 사용자를 가져오는 메서드 (예시)
        Store store = storeId != null ? storeService.getStoreOrException(storeId) : null;

        return orderRepository
                .findAllByOptionalStoreAndUser(store, user, pageable)
                .map(OrderResponse::from);
    }

    public Page<OrderResponse> getAllOrdersByUser(Pageable pageable, Long userId) {
        User user = userService.getUserOrException(userId);
        return orderRepository.findAllByUser(user, pageable)
                .map(OrderResponse::from);
    }

    public Page<OrderResponse> getStoreOrders(Pageable pageable, String storeId, Long customerId, Long ownerId) {
        Store store = storeService.getStoreOrException(storeId);

        // store가 owner의 것이 맞는지 체크
        orderValidator.checkStoreOwnerPermission(store, ownerId);

        // store 와 customer를 모두 만족하는 order
        return orderRepository
                .findByStoreAndOptionalUser(store,
                        customerId != null ? userService.getUserOrException(customerId) : null,
                        pageable)
                .map(OrderResponse::from);
    }
=======
=======
>>>>>>> c1fc115 ([Feat] 주문 요청)
    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;

    @Transactional
    public String createOrder(Long userId, OrderCreateRequest request) {
        // 1. 사용자, 주소, 가게 정보 조회
        User user = userService.getUserOrException(userId);
        Address address = addressService.getAddressOrException(request.addressId());
        Store store = storeService.getStoreOrException(request.storeId());

        // 2. 주문 총 가격 계산
        int totalPrice = calculateTotalPrice(request.menus());

        // 3. Order 엔티티 생성
        Order order = Order.create(user, address, store, OrderType.of(request.type()), totalPrice, request.demand());
        orderRepository.save(order);

        // 4. OrderMenu 엔티티 생성 및 저장
        request.menus().forEach(menuInfo -> {
            Menu menu = menuService.getMenuOrException(menuInfo.menuId());
            OrderMenu orderMenu = OrderMenu.create(menu, order, menuInfo.count());
            orderMenuRepository.save(orderMenu);
            order.getOrderMenus().add(orderMenu);
        });

        return order.getOrderId();
    }

    private int calculateTotalPrice(List<OrderMenuInfo> menus) {
        return menus.stream()
                .mapToInt(menuInfo -> {
                    Menu menu = menuService.getMenuOrException(menuInfo.menuId());
                    return menu.getPrice() * menuInfo.count();
                })
                .sum();
    }

    @Transactional
    public String updateOrderStatus(String orderId) {
        Order order = getOrderOrException(orderId);
        order.approve();
        return order.getOrderId();
    }

    @Transactional
    public String deleteOrder(String orderId) {
        Order order = getOrderOrException(orderId);
        order.cancel();
        return order.getOrderId();
    }

    public Order getOrderOrException(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new CodeBloomException(ErrorCode.ORDER_NOT_FOUND));
    }
>>>>>>> 09c755a ([Refactor] OrderService 와 UserService의 getXxxOrException() 메서드 호출로 대체)
}
