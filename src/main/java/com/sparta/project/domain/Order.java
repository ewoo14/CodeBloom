package com.sparta.project.domain;

import com.sparta.project.domain.enums.OrderStatus;
import com.sparta.project.domain.enums.OrderType;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name = "p_order")
public class Order extends BaseEntity { // 주문
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", length = 36, nullable = false, updatable = false)
    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "type", nullable = false) // 주문 유형 (온라인/오프라인)
    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Column(name = "status", nullable = false) // 주문 상태 (대기/승인/취소)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'WAITING'")
    private OrderStatus status;

    @Column(name = "order_price", nullable = false) // 주문 가격
    private Integer orderPrice;

    @Column(name = "demand", length = 50) // 요청 사항
    private String demand;

    @OneToMany(mappedBy = "order")
    private List<OrderMenu> orderMenus = new ArrayList<>();

    @Builder
    private Order(User user, Address address, Store store, OrderType type, Integer orderPrice, String demand) {
        this.user = user;
        this.address = address;
        this.store = store;
        this.type = type;
        this.status = OrderStatus.WAITING;
        this.orderPrice = orderPrice;
        this.demand = demand;
    }

    public static Order create(User user, Address address, Store store, OrderType type, Integer orderPrice, String demand) {
        return Order.builder()
                .user(user)
                .address(address)
                .store(store)
                .type(type)
                .orderPrice(orderPrice)
                .demand(demand)
                .build();
    }

    public void approve() {
        if (this.status != OrderStatus.WAITING) throw new CodeBloomException(ErrorCode.INVALID_ORDER_STATUS_CHANGE);
        this.status = OrderStatus.APPROVED;
    }

}