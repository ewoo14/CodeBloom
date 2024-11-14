package com.sparta.project.repository;

import com.sparta.project.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PaymentRepository extends JpaRepository<Payment, String>, QuerydslPredicateExecutor<Payment> {
}
