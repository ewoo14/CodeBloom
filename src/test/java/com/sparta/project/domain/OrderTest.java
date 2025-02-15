package com.sparta.project.domain;

import com.sparta.project.domain.enums.OrderStatus;
import com.sparta.project.domain.enums.OrderType;
import com.sparta.project.domain.enums.Role;
import com.sparta.project.exception.CodeBloomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    private User user;
    private Address address;
    private Store store;

    @BeforeEach
    void setUp() {
        user = User.create("gritold", "1234", "nick", Role.CUSTOMER);
        address = Address.create(user, "서울시", "서울시군구", null, null, null, true);
        store = Store.create("착한중식당", "착한중식당 상세 설명", "주소", null, null, null);
    }

    @DisplayName("Order 생성 시 초기 상태는 WAITING이다.")
    @Test
    void createOrderWithInitialStatus() {
        // given & when
        Order order = Order.create(user, address, store, OrderType.ONLINE, 50000, "천천히 갖다 주세요.");

        // then
        assertThat(order.getStatus()).isEqualTo(OrderStatus.WAITING);
    }

    @DisplayName("status 를 APPROVED로 변경한다.")
    @Test
    void approve() {
        // given
        Order order = Order.create(user, address, store, OrderType.ONLINE, 50000, "천천히 갖다 주세요.");

        // when
        order.approve();

        // then
        assertThat(order.getStatus()).isEqualTo(OrderStatus.APPROVED);
    }

    @DisplayName("이미 승인한 주문은 예외를 반환한다.")
    @Test
    void approveWhenAlreadyApproved() {
        // given
        Order order = Order.create(user, address, store, OrderType.ONLINE, 50000, "천천히 갖다 주세요.");
        order.approve();

        // when & then
        assertThatThrownBy(() -> order.approve()).isInstanceOf(CodeBloomException.class);
    }

    @DisplayName("status를 CANCELED로 변경한다.")
    @Test
    void cancel() {
        // given
        Order order = Order.create(user, address, store, OrderType.ONLINE, 50000, "천천히 갖다 주세요.");

        // when
        order.cancel(user.getUserId());

        // then
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCELED);
    }

    @DisplayName("이미 취소된 주문은 예외를 반환한다.")
    @Test
    void cancelWhenAlreadyCanceled() {
        // given
        Order order = Order.create(user, address, store, OrderType.ONLINE, 50000, "천천히 갖다 주세요.");
        order.cancel(user.getUserId());

        // when & then
        assertThatThrownBy(() -> order.cancel(user.getUserId()))
                .isInstanceOf(CodeBloomException.class);
    }

    @DisplayName("이미 승인된 주문은 취소할 때 예외를 반환한다.")
    @Test
    void cancelWhenAlreadyApproved() {
        // given
        Order order = Order.create(user, address, store, OrderType.ONLINE, 50000, "천천히 갖다 주세요.");
        order.approve();

        // when & then
        assertThatThrownBy(() -> order.cancel(user.getUserId()))
                .isInstanceOf(CodeBloomException.class);
    }

}