package com.sparta.project.service;


import com.sparta.project.domain.StoreRequest;
import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.Role;
import com.sparta.project.domain.enums.StoreRequestStatus;
import com.sparta.project.dto.store.StoreCreateData;
import com.sparta.project.dto.storerequest.StoreCreateRequest;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.StoreRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreRequestService {

    private final UserService userService;
    private final StoreService storeService;
    private final StoreCategoryService categoryService;
    private final LocationService locationService;
    private final StoreRequestRepository storeRequestRepository;

    @Transactional
    public void createStoreRequest(final long userId, final StoreCreateRequest request) {
        User user = userService.getUserOrException(userId);
        if(user.getRole()!= Role.OWNER) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
        storeRequestRepository.save(StoreRequest.create(
                request.name(), request.description(), request.address(), user,
                categoryService.getStoreCategoryOrException(request.storeCategoryId()),
                locationService.getLocationOrException(request.locationId())
        ));
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

    private void checkAlreadyChanged(StoreRequestStatus before, StoreRequestStatus change) {
        if(before==change) {
            throw new CodeBloomException(ErrorCode.ALREADY_PROCESSED);
        }
    }

    private StoreRequest getStoreRequestOrException(String id) {
        return storeRequestRepository.findById(id).orElseThrow(()->
                new CodeBloomException(ErrorCode.STORE_REQUEST_NOT_FOUND));
    }

}
