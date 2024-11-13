package com.sparta.project.service;


import com.sparta.project.domain.StoreRequest;
import com.sparta.project.domain.User;
<<<<<<< HEAD
import com.sparta.project.domain.enums.StoreRequestStatus;
import com.sparta.project.dto.store.StoreCreateData;
import com.sparta.project.dto.storerequest.StoreCreateRequest;
import com.sparta.project.dto.storerequest.StoreRequestAdminResponse;
import com.sparta.project.dto.storerequest.StoreRequestResponse;
import com.sparta.project.dto.storerequest.StoreRequestUpdateRequest;
import com.sparta.project.dto.storerequest.StoreRequestOwnerResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.storerequest.StoreRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.storerequest.StoreCreateRequest;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.StoreRequestRepository;
import lombok.RequiredArgsConstructor;
>>>>>>> 4f6677d ([Feat] 가게 생성을 요청하는 기능)
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreRequestService {

    private final UserService userService;
<<<<<<< HEAD
    private final StoreService storeService;
    private final StoreCategoryService categoryService;
    private final LocationService locationService;
<<<<<<< HEAD
=======
    private final StoreCategoryService categoryService;
    private final StoreLocationService locationService;
>>>>>>> 4f6677d ([Feat] 가게 생성을 요청하는 기능)
=======
>>>>>>> 38b9494 ([Fix] StoreLocationService 삭제 및 LocationService로의 통합)
    private final StoreRequestRepository storeRequestRepository;

    @Transactional
    public void createStoreRequest(final long userId, final StoreCreateRequest request) {
        User user = userService.getUserOrException(userId);
<<<<<<< HEAD
=======
        if(user.getRole()!= Role.OWNER) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
>>>>>>> 4f6677d ([Feat] 가게 생성을 요청하는 기능)

        storeRequestRepository.save(StoreRequest.create(
                request.name(), request.description(), request.address(), user,
                categoryService.getStoreCategoryOrException(request.storeCategoryId()),
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 38b9494 ([Fix] StoreLocationService 삭제 및 LocationService로의 통합)
                locationService.getLocationOrException(request.locationId())
        ));
    }

    @Transactional
    public void updateStoreRequest(long userId, String storeRequestId, final StoreRequestUpdateRequest request) {
        StoreRequest storeRequest = getStoreRequestOrException(storeRequestId);
        if(storeRequest.getOwner().getUserId() != userId) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
        if(storeRequest.getStatus() != StoreRequestStatus.WAITING) {
            throw new CodeBloomException(ErrorCode.STORE_REQUEST_UNABLE_MODIFY);
        }
        storeRequest.updateInfo(
                request.name(), request.description(), request.address(),
                (request.categoryId()!=null)?categoryService.getStoreCategoryOrException(request.categoryId()):null,
                (request.locationId()!=null)?locationService.getLocationOrException(request.locationId()):null
        );
    }

    @Transactional
    public void approveStoreRequest(String storeRequestId) {
        StoreRequest storeRequest = getStoreRequestOrException(storeRequestId);
        checkAlreadyChanged(storeRequest.getStatus(), StoreRequestStatus.APPROVE);
        storeRequest.approve();
        storeService.createStore(StoreCreateData.from(storeRequest));
    }

    @Transactional
    public void rejectStoreRequest(long userId, String storeRequestId, String rejectionReason) {
        StoreRequest storeRequest = getStoreRequestOrException(storeRequestId);
        checkAlreadyChanged(storeRequest.getStatus(), StoreRequestStatus.REJECT);
        storeRequest.reject(rejectionReason);
        storeRequest.deleteBase(String.valueOf(userId));
    }

    @Transactional(readOnly = true)
    public StoreRequestResponse getStoreRequestBy(long userId, String storeRequestId, boolean isAdmin) {
        StoreRequest storeRequest = getStoreRequestOrException(storeRequestId);
        if(!isAdmin && storeRequest.getOwner().getUserId() != userId) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
        return (isAdmin)
                ? StoreRequestAdminResponse.from(storeRequest)
                : StoreRequestOwnerResponse.from(storeRequest);
    }

    @Transactional(readOnly = true)
    public Page<StoreRequestOwnerResponse> getAllUserStoreRequests(
            long userId,
            Pageable pageable,
            StoreRequestStatus status,
            String storeName) {
        return storeRequestRepository.findUserStoreRequestsWith(pageable, userId, status, storeName)
                .map(StoreRequestOwnerResponse::from);
    }

    @Transactional(readOnly = true)
    public Page<StoreRequestAdminResponse> getAllStoreRequests(
            Pageable pageable,
            String categoryId,
            StoreRequestStatus status,
            String storeName) {
        return storeRequestRepository.findStoreRequestsWith(pageable, categoryId, status, storeName)
                .map(StoreRequestAdminResponse::from);
    }

    private void checkAlreadyChanged(StoreRequestStatus before, StoreRequestStatus change) {
        if(before==change) {
            throw new CodeBloomException(ErrorCode.ALREADY_PROCESSED);
        }
    }

    private StoreRequest getStoreRequestOrException(String id) {
        return storeRequestRepository.findById(id).orElseThrow(()->
                new CodeBloomException(ErrorCode.STORE_REQUEST_NOT_FOUND));
=======
                locationService.getStoreLocationOrException(request.locationId())
        ));

>>>>>>> 4f6677d ([Feat] 가게 생성을 요청하는 기능)
    }

}
