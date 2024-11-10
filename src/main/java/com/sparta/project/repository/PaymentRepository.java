package com.sparta.project.repository;

import com.sparta.project.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PaymentRepository extends JpaRepository<Payment, String>, QuerydslPredicateExecutor<Payment> {
=======

public interface PaymentRepository extends JpaRepository<Payment, String> {
>>>>>>> 054108d (결제 기능 구현 Service)
}
