package com.sparta.project.service;

import com.sparta.project.domain.Order;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order getUserOrException(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(()->
                new CodeBloomException(ErrorCode.ORDER_NOT_FOUND));
    }
}
