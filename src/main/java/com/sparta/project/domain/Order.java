package com.sparta.project.domain;

import com.sparta.project.domain.enums.OrderStatus;
import com.sparta.project.domain.enums.OrderType;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
<<<<<<< HEAD
<<<<<<< HEAD

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
=======
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
>>>>>>> 7c82438 ([Refactor] 생성자 메서드 빌더 패턴 적용)
=======
>>>>>>> 054108d (결제 기능 구현 Service)

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name = "p_order")
public class Order extends BaseEntity { // 주문
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 5c7a118 ([Feat] 주문 승인)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", length = 36, nullable = false, updatable = false)
    private String orderId;
<<<<<<< HEAD
=======
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="order_id", length=36, nullable=false, updatable=false)
	private String orderId;
>>>>>>> f1fe45f ([Fix] Order, Payment 엔티티의 빌더에 id를 넣는 부분 삭제)

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
=======
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;
>>>>>>> 699e908 ([Feat] @OnDelete 설정 제거)

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
=======
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="address_id", nullable=false)
	private Address address;
=======

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
>>>>>>> 5c7a118 ([Feat] 주문 승인)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

<<<<<<< HEAD
	@Column(name="type", nullable=false) // 주문 유형 (온라인/오프라인)
	@Enumerated(EnumType.STRING)
	private OrderType type;
>>>>>>> 6ad0ce7 ([Feat] Order FK로 Address, Store 추가, Payment FK로 User 추가)

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
=======
	@Column(name="status", nullable=false) // 주문 상태 (대기/승인/취소)
	@Enumerated(EnumType.STRING)
	@ColumnDefault("'WAITING'")
	private OrderStatus status;
>>>>>>> 7c82438 ([Refactor] 생성자 메서드 빌더 패턴 적용)

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
        if (this.status != OrderStatus.WAITING) throw new CodeBloomException(ErrorCode.CANNOT_APPROVE_ORDER);
<<<<<<< HEAD
        this.status = OrderStatus.APPROVED;
    }

    public void cancel(Long userId) {
        if (this.status != OrderStatus.WAITING) {
            throw new CodeBloomException(ErrorCode.CANNOT_CANCEL_ORDER);
        }

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(this.getCreatedAt(), now);

        if (duration.toMinutes() > 5) {
            throw new CodeBloomException(ErrorCode.ORDER_CANCELLATION_TIME_EXPIRED);
        }

        this.status = OrderStatus.CANCELED;
        deleteBase(String.valueOf(userId));
    }
=======
    @Column(name = "type", nullable = false) // 주문 유형 (온라인/오프라인)
    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Column(name = "status", nullable = false) // 주문 상태 (대기/승인/취소)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'WAITING'")
    private OrderStatus status;

    @Column(name = "order_price", nullable = false) // 주문 가격
    private Integer orderPrice;
>>>>>>> 5c7a118 ([Feat] 주문 승인)

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
=======
>>>>>>> c772cf7 ([Feat] 주문 취소)
        this.status = OrderStatus.APPROVED;
    }

    public void cancel(Long userId) {
        if (this.status != OrderStatus.WAITING) {
            throw new CodeBloomException(ErrorCode.CANNOT_CANCEL_ORDER);
        }

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(this.getCreatedAt(), now);

        if (duration.toMinutes() > 5) {
            throw new CodeBloomException(ErrorCode.ORDER_CANCELLATION_TIME_EXPIRED);
        }

        this.status = OrderStatus.CANCELED;
        deleteBase(String.valueOf(userId));
    }

}