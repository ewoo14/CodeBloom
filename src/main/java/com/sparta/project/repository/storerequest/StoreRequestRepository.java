package com.sparta.project.repository.storerequest;

import com.sparta.project.domain.StoreRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRequestRepository extends JpaRepository<StoreRequest, String>, StoreRequestCustomRepository {
}
