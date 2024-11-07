package com.sparta.project.repository;

import com.sparta.project.domain.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, String> {
}
