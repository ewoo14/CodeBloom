package com.sparta.project.service;

import com.sparta.project.domain.Store;
import com.sparta.project.dto.store.StoreCreateData;
import com.sparta.project.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public void createStore(final StoreCreateData data) {
        storeRepository.save(Store.create(
                data.name(), data.description(), data.address(),
                data.owner(), data.storeCategory(), data.location()
        ));
    }

}
