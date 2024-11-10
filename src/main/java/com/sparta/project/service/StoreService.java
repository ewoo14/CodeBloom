package com.sparta.project.service;

import com.sparta.project.domain.Store;
import com.sparta.project.dto.store.StoreResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreResponse getStoreById(String storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.STORE_NOT_FOUND));

        return StoreResponse.from(store);
    }
}
