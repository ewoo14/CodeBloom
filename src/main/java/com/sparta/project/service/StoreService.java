package com.sparta.project.service;

import com.sparta.project.domain.Location;
import com.sparta.project.domain.Store;
import com.sparta.project.domain.StoreCategory;
import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.store.StoreResponse;
import com.sparta.project.dto.store.StoreUpdateResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.LocationRepository;
import com.sparta.project.repository.StoreCategoryRepository;
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
    private final LocationRepository locationRepository;
    private final StoreCategoryRepository storeCategoryRepository;

    public void getMyStores(String username, int page, int size, int sortBy) {

    }

    public Store getStoreOrException(String storeId) {
        return storeRepository.findById(storeId).orElseThrow(() -> new CodeBloomException(ErrorCode.STORE_NOT_FOUND));
    }

    public StoreResponse getStoreById(String storeId) {
        return StoreResponse.from(getStoreOrException(storeId));
    }

    @Transactional
    public StoreUpdateResponse updateStore(String storeId, String storeName, String description,
                                           String locationId, String categoryId, Long userId) {
        User user = userService.getUserOrException(userId);
        checkPermission(user);

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.STORE_NOT_FOUND));

        Location location = null;
        if (locationId != null) {
            location = storeLocationService.getStoreLocationOrException(locationId);
        }

        StoreCategory storeCategory = null;
        if (categoryId != null) {
            storeCategory = storeCategoryService.getStoreCategoryOrException(categoryId);
        }

        store.update(storeName, description, location, storeCategory);

        // StoreUpdateResponse 생성 및 반환 로직
        return StoreUpdateResponse.from(store);
    }

    @Transactional
    public void deleteStore(String storeId, String username) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.STORE_NOT_FOUND));

        store.deleteBase(username);
    }

    // 권한 확인
    private void checkPermission(User user) {
        if (user.getRole() == Role.CUSTOMER) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }
}
