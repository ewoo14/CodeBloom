package com.sparta.project.repository;

import com.sparta.project.domain.Order;
<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.domain.Store;
import com.sparta.project.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, String> {

    // store 가 null 인 경우 모든 거 반환
    @Query("SELECT o FROM Order o WHERE (:store IS NULL OR o.store = :store) AND o.user = :user")
    Page<Order> findAllByOptionalStoreAndUser(@Param("store") Store store, @Param("user") User user, Pageable pageable);

    Page<Order> findAllByUser(User user, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.store = :store " +
            "AND (:user IS NULL OR o.user = :user)")
    Page<Order> findByStoreAndOptionalUser(
            @Param("store") Store store,
            @Param("user") User user,
            Pageable pageable
    );
=======
=======
import com.sparta.project.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> 6336dc6 ([Feat] 자신의 주문내역 목록 조회)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, String> {
<<<<<<< HEAD
>>>>>>> 054108d (결제 기능 구현 Service)
=======

    // store 가 null 인 경우 모든 거 반환
    @Query("SELECT o FROM Order o WHERE (:store IS NULL OR o.store = :store)")
    Page<Order> findAllByStoreNullable(@Param("store") Store store, Pageable pageable);
<<<<<<< HEAD
>>>>>>> 6336dc6 ([Feat] 자신의 주문내역 목록 조회)
=======

    Page<Order> findAllByUser(User user, Pageable pageable);
>>>>>>> 9d9d1b7 ([Feat] 고객의 주문내역 목록 조회)
}
