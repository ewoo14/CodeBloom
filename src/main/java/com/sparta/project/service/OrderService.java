package com.sparta.project.service;

import com.sparta.project.domain.*;
import com.sparta.project.domain.enums.OrderType;
import com.sparta.project.dto.order.OrderCreateRequest;
import com.sparta.project.dto.order.OrderMenuInfo;
import com.sparta.project.dto.order.OrderResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.OrderMenuRepository;
import com.sparta.project.repository.OrderRepository;
import com.sparta.project.util.PermissionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final UserService userService;
    private final AddressService addressService;
    private final MenuService menuService;
    private final StoreService storeService;
    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final PermissionValidator permissionValidator;

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
        permissionValidator.checkOrderApprovePermission(order, userId);
        order.approve();
        return order.getOrderId();
    }

    // 주문 취소와 삭제는 같은 개념
    @Transactional
    public String cancelOrder(String orderId, Long userId) {
        Order order = getOrderOrException(orderId);
        permissionValidator.checkOrderCancelPermission(order, userId);
        order.cancel(userId);
        return order.getOrderId();
    }

    public OrderResponse getOrderById(String orderId, long userId) {
        Order order = getOrderOrException(orderId);
        permissionValidator.checkOrderOwner(userId, order.getUser().getUserId());
        return OrderResponse.from(order);
    }

    public Order getOrderOrException(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new CodeBloomException(ErrorCode.ORDER_NOT_FOUND));
    }
}
