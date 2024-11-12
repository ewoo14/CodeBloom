package com.sparta.project.service;

import com.sparta.project.domain.Location;
import com.sparta.project.domain.Store;
import com.sparta.project.domain.StoreCategory;
import com.sparta.project.domain.User;
import com.sparta.project.dto.store.StoreCreateData;
import com.sparta.project.dto.store.StoreResponse;
import com.sparta.project.dto.store.StoreUpdateRequest;
import com.sparta.project.dto.store.StoreUpdateResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final UserService userService;
    private final StoreLocationService storeLocationService;
    private final StoreCategoryService storeCategoryService;
    private final StoreRepository storeRepository;

    public void createStore(final StoreCreateData data) {
        storeRepository.save(Store.create(
                data.name(), data.description(), data.address(),
                data.owner(), data.storeCategory(), data.location()
        ));
    }

    public Store getStoreOrException(String storeId) {
        return storeRepository.findById(storeId).orElseThrow(() -> new CodeBloomException(ErrorCode.STORE_NOT_FOUND));
    }

    public StoreResponse getStoreById(String storeId) {
        return StoreResponse.from(getStoreOrException(storeId));
    }

    @Transactional
    public StoreUpdateResponse updateStore(String storeId, StoreUpdateRequest storeUpdateRequest) {
        Store store = getStoreOrException(storeId);

        Location location = null;
        if (storeUpdateRequest.locationId() != null) {
            location = storeLocationService.getStoreLocationOrException(storeUpdateRequest.locationId());
        }

        StoreCategory storeCategory = null;
        if (storeUpdateRequest.categoryId() != null) {
            storeCategory = storeCategoryService.getStoreCategoryOrException(storeUpdateRequest.categoryId());
        }

        store.update(storeUpdateRequest.storeName(), storeUpdateRequest.description(), location, storeCategory);

        // StoreUpdateResponse 생성 및 반환 로직
        return StoreUpdateResponse.from(store);
    }

    @Transactional
    public void deleteStore(String storeId, Long userId) {
        Store store = getStoreOrException(storeId);
        User user = userService.getUserOrException(userId);
        store.deleteBase(user.getUsername());
    }

}
