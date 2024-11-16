package com.sparta.project.repository;

import com.sparta.project.domain.Order;
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
    Page<Order> findAllByStoreNullableAndUser(@Param("store") Store store, @Param("user") User user, Pageable pageable);

    Page<Order> findAllByUser(User user, Pageable pageable);
}
