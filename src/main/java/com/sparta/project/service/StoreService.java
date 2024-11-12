package com.sparta.project.service;

import com.sparta.project.domain.Location;
import com.sparta.project.domain.Store;
<<<<<<< HEAD
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
=======
import com.sparta.project.domain.StoreCategory;
<<<<<<< HEAD
>>>>>>> f337ba7 ([Feat] 음식점 정보 수정 기능 Service)
=======
import com.sparta.project.domain.User;
<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.domain.enums.Role;
>>>>>>> 2ae41d6 ([Feat] 음식점 정보 수정 기능 service)
=======
>>>>>>> c80f876 ([Feat] 음식점 정보 삭제 완료)
=======
import com.sparta.project.dto.store.StoreCreateData;
>>>>>>> 2408cc2 ([build] 가게 요청 도메인의 작업과 충돌되는 부분 해결)
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
<<<<<<< HEAD
>>>>>>> b8d01e9 ([Feat] 음식점 상세 조회 기능 Service 및 ServiceTest)
=======

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
<<<<<<< HEAD
>>>>>>> f337ba7 ([Feat] 음식점 정보 수정 기능 Service)
=======

    @Transactional
    public void deleteStore(String storeId, Long userId) {
        Store store = getStoreOrException(storeId);
        User user = userService.getUserOrException(userId);
        store.deleteBase(user.getUsername());
    }
<<<<<<< HEAD
>>>>>>> 2150278 ([Feat] 음식점 정보 삭제 기능 Service)
=======

<<<<<<< HEAD
    // 권한 확인
    private void checkPermission(User user) {
        if (user.getRole() == Role.CUSTOMER) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }
>>>>>>> 2ae41d6 ([Feat] 음식점 정보 수정 기능 service)
=======
>>>>>>> c80f876 ([Feat] 음식점 정보 삭제 완료)
}
