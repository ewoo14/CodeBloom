package com.sparta.project.controller;

import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.storerequest.StoreCreateRequest;
import com.sparta.project.service.StoreRequestService;
import com.sparta.project.util.PermissionValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/store-requests")
public class StoreRequestController {

    private final PermissionValidator permissionValidator;
    private final StoreRequestService storeRequestService;

    @PostMapping
    public ApiResponse<Void> createStoreRequest(Authentication authentication,
                                                @Valid @RequestBody StoreCreateRequest request) {
        storeRequestService.createStoreRequest(Long.parseLong(authentication.getName()), request);
        return ApiResponse.success();
    }

    @PostMapping("/{request_id}")
    public ApiResponse<Void> updateStoreRequest(Authentication authentication,
                                                @PathVariable String request_id) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        storeRequestService.approveStoreRequest(request_id);
        return ApiResponse.success();
    }

//
//    // 음식점 생성 요청 반려(MANAGER, MASTER)
//    @DeleteMapping("/{request_id}")
//    public ApiResponse<Void> deleteStoreRequest(@PathVariable String request_id) {
//        storeRequestService.deleteStoreRequest(request_id);
//        return ApiResponse.success();
//    }

//
//    // 자신의 요청 목록 조회(OWNER)
//    @GetMapping("/my")
//    public ApiResponse<PageResponse<StoreRequestResponse>> getMyStoreRequests(
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<StoreRequestResponse> myRequests = storeRequestService.getMyStoreRequests(page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(myRequests));
//    }
//
//    // 음식점 생성 요청 상세 조회(OWNER)
//    @GetMapping("/my/{request_id}")
//    public ApiResponse<PageResponse<StoreRequestResponse>> getStoreRequestById(
//            @PathVariable String request_id,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<StoreRequestResponse> myRequest = storeRequestService.getStoreRequestById(request_id, page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(myRequest));
//    }
//
//    // 음식점 생성 요청 목록 조회(MANAGER, MASTER)
//    @GetMapping
//    public ApiResponse<PageResponse<StoreRequestResponse>> getAllStoreRequests(
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<StoreRequestResponse> storeRequests = storeRequestService.getAllStoreRequests(page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(storeRequests));
//    }
//
//    // 음식점 생성 요청 상세 조회(MANAGER, MASTER)
//    @GetMapping("/{request_id}")
//    public ApiResponse<PageResponse<StoreRequestResponse>> getStoreRequestById(
//            @PathVariable String request_id,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<StoreRequestResponse> storeRequest = storeRequestService.getStoreRequestById(request_id, page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(storeRequest));
//    }
//
//

}
