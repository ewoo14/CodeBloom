package com.sparta.project.controller;

import com.sparta.project.domain.enums.Role;
import com.sparta.project.domain.enums.StoreRequestStatus;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.storerequest.*;
import com.sparta.project.permission.PermissionValidator;
import com.sparta.project.service.StoreRequestService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/store-requests")
public class StoreRequestController {

    private final PermissionValidator permissionValidator;
    private final StoreRequestService storeRequestService;

    @PostMapping
    public ApiResponse<Void> createStoreRequest(Authentication authentication,
                                                @Valid @RequestBody StoreCreateRequest request) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
        storeRequestService.createStoreRequest(Long.parseLong(authentication.getName()), request);
        return ApiResponse.success();
    }

    // 음식점 생성 요청 수정 (OWNER)
    @PatchMapping("/{request_id}")
    public ApiResponse<Void> updateStoreRequest(Authentication authentication,
                                                @PathVariable String request_id,
                                                @NotNull @RequestBody StoreRequestUpdateRequest request) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
        storeRequestService.updateStoreRequest(Long.parseLong(authentication.getName()), request_id, request);
        return ApiResponse.success();
    }

    @PostMapping("/{request_id}")
    public ApiResponse<Void> approveStoreRequest(Authentication authentication,
                                                 @PathVariable String request_id) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        storeRequestService.approveStoreRequest(request_id);
        return ApiResponse.success();
    }

    // 음식점 생성 요청 반려(MANAGER, MASTER)
    @DeleteMapping("/{request_id}")
    public ApiResponse<Void> rejectStoreRequest(Authentication authentication,
                                                @PathVariable String request_id,
                                                @NotBlank @RequestBody String rejectionReason) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        storeRequestService.rejectStoreRequest(Long.parseLong(authentication.getName()), request_id, rejectionReason);
        return ApiResponse.success();
    }

    // 자신의 음식점 생성 요청 상세 조회(OWNER)
    @GetMapping("/my/{request_id}")
    public ApiResponse<StoreRequestResponse> getOwnerStoreRequestById(Authentication authentication,
                                                                      @PathVariable String request_id) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
        StoreRequestResponse result = storeRequestService.getStoreRequestBy(
                Long.parseLong(authentication.getName()), request_id, false
        );
        return ApiResponse.success(result);
    }

    // 음식점 생성 요청 상세 조회(MANAGER, MASTER)
    @GetMapping("/{request_id}")
    public ApiResponse<StoreRequestResponse> getStoreRequestById(Authentication authentication,
                                                                 @PathVariable String request_id) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        StoreRequestResponse result = storeRequestService.getStoreRequestBy(
                Long.parseLong(authentication.getName()), request_id, true
        );
        return ApiResponse.success(result);
    }

    // 자신의 요청 목록 조회(OWNER)
    @GetMapping("/my")
    public ApiResponse<PageResponse<StoreRequestOwnerResponse>> getOwnerStoreRequests(
            Authentication authentication,
            @PageableDefault(size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Direction.DESC)
            })
            Pageable pageable,
            @RequestParam(value = "status", required = false) StoreRequestStatus status,
            @RequestParam(value = "storeName", required = false) String storeName) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
        Page<StoreRequestOwnerResponse> result = storeRequestService.getAllUserStoreRequests(
                Long.parseLong(authentication.getName()), pageable, status, storeName
        );
        return ApiResponse.success(PageResponse.of(result));
    }

    // 음식점 생성 요청 목록 조회(MANAGER, MASTER)
    @GetMapping
    public ApiResponse<PageResponse<StoreRequestAdminResponse>> getAllStoreRequests(
            Authentication authentication,
            @PageableDefault(size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Direction.DESC)
            })
            Pageable pageable,
            @RequestParam(value = "categoryId", required = false) String categoryId,
            @RequestParam(value = "status", required = false) StoreRequestStatus status,
            @RequestParam(value = "storeName", required = false) String storeName) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        Page<StoreRequestAdminResponse> result = storeRequestService.getAllStoreRequests(
                pageable, categoryId, status, storeName
        );
        return ApiResponse.success(PageResponse.of(result));
    }

}
