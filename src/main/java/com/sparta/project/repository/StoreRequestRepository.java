package com.sparta.project.repository;

import com.sparta.project.domain.StoreRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRequestRepository extends JpaRepository<StoreRequest, String> {
}
