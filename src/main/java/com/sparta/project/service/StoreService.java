package com.sparta.project.service;

import com.sparta.project.domain.Store;
<<<<<<< HEAD
import com.sparta.project.dto.store.StoreCreateData;
import com.sparta.project.dto.store.StoreResponse;
import com.sparta.project.dto.store.StoreUpdateRequest;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.StoreQueryRepository;
import com.sparta.project.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final UserService userService;
    private final LocationService locationService;
    private final StoreCategoryService storeCategoryService;
    private final StoreRepository storeRepository;
    private final StoreQueryRepository storeQueryRepository;

    public Page<StoreResponse> getMyStores(int page, int size, Long ownerId) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Store> storePage = storeRepository.findAllByOwner(userService.getUserOrException(ownerId), pageable);
        return storePage.map(StoreResponse::from);
    }

    public Page<StoreResponse> getAllStores(String storeName, String cagetoryId, String menuName, Pageable pageable) {
        Page<Store> storePage = storeQueryRepository.searchWithPage(
                cagetoryId != null ? storeCategoryService.getStoreCategoryOrException(cagetoryId) : null,
                storeName, menuName, pageable);
        return storePage.map(StoreResponse::from);
    }

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
    public String updateStore(String storeId, StoreUpdateRequest request) {
        Store store = getStoreOrException(storeId);

        store.update(request.storeName(),
                request.description(),
                request.locationId() != null ? locationService.getLocationOrException(request.locationId()) : null,
                request.categoryId() != null ? storeCategoryService.getStoreCategoryOrException(request.categoryId()) : null);

        return storeId;
    }

    @Transactional
    public void deleteStore(String storeId, Long userId) {
        Store store = getStoreOrException(storeId);
        store.deleteBase(String.valueOf(userId));
    }

=======
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
>>>>>>> b8d01e9 ([Feat] 음식점 상세 조회 기능 Service 및 ServiceTest)
}
