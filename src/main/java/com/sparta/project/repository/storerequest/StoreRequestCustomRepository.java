package com.sparta.project.repository.storerequest;

import com.sparta.project.domain.StoreRequest;
import com.sparta.project.domain.enums.StoreRequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreRequestCustomRepository {
    Page<StoreRequest> findStoreRequestsWith(
            Pageable pageable, String categoryId, StoreRequestStatus status, String storeName
    );
    Page<StoreRequest> findUserStoreRequestsWith(
            Pageable pageable, Long userId, StoreRequestStatus status, String storeName
    );
}
