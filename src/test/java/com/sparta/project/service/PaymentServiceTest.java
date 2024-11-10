package com.sparta.project.service;

import com.sparta.project.client.PgClient;
import com.sparta.project.domain.Order;
import com.sparta.project.domain.Payment;
import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.OrderType;
import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.payment.PaymentCreateResponse;
import com.sparta.project.repository.OrderRepository;
import com.sparta.project.repository.PaymentRepository;
import com.sparta.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class PaymentServiceTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @MockBean
    PgClient pgClient;

    private Order testOrder;
    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = createUser("griotold");
        userRepository.save(testUser);
        testOrder = createOrder(testUser); // Order 객체 생성 로직 추가
        orderRepository.save(testOrder);
    }

    @Test
    void createPayment_Success() {
        // given
        String orderId = testOrder.getOrderId();
        String type = "CARD";
        int paymentPrice = 10000;
        String pgName = "TOSS";
        String username = "griotold";

        when(pgClient.requestPayment(any(Payment.class))).thenReturn(true);

        // when
        PaymentCreateResponse response = paymentService.createPayment(orderId, type, paymentPrice, pgName, username);

        // then
        assertThat(response).isNotNull();

        Optional<Payment> savedPayment = paymentRepository.findById(response.paymentId());
        assertThat(savedPayment.isPresent());
        assertThat(testOrder.getOrderId()).isEqualTo(savedPayment.get().getOrder().getOrderId());
    }

    private User createUser(String username) {
        return User.create(username, "1234", "닉네임", Role.CUSTOMER);
    }

    private Order createOrder(User user) {
        return Order.create("UUID", user, null, null, OrderType.ONLINE, 10_000, "요구사항");
    }
}