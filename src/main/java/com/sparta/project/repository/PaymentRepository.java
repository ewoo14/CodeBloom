package com.sparta.project.repository;

import com.sparta.project.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PaymentRepository extends JpaRepository<Payment, String>, QuerydslPredicateExecutor<Payment> {
=======

public interface PaymentRepository extends JpaRepository<Payment, String> {
>>>>>>> 054108d (결제 기능 구현 Service)
=======
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PaymentRepository extends JpaRepository<Payment, String>, QuerydslPredicateExecutor<Payment> {
>>>>>>> cb54b05 ([Feat] 결제 내역 목록 조회 메서드 3개 구현 및 결제 요청 수정)
}
