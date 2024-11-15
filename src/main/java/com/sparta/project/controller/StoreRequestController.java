<<<<<<< HEAD
<<<<<<< HEAD
package com.sparta.project.controller;

<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.domain.enums.Role;
import com.sparta.project.domain.enums.StoreRequestStatus;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
<<<<<<< HEAD
import com.sparta.project.dto.storerequest.*;
import com.sparta.project.permission.PermissionValidator;
=======
import com.sparta.project.dto.storerequest.StoreCreateRequest;
import com.sparta.project.dto.storerequest.StoreRequestAdminResponse;
import com.sparta.project.dto.storerequest.StoreRequestResponse;
import com.sparta.project.dto.storerequest.StoreRequestUpdateRequest;
import com.sparta.project.dto.storerequest.StoreRequestOwnerResponse;
>>>>>>> d5585a5 ([Feat] 음식점 생성 요청 정보 상세 조회 API)
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
=======
package com.sparta.project.controller;

import com.sparta.project.domain.enums.Role;
import com.sparta.project.domain.enums.StoreRequestStatus;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.storerequest.StoreCreateRequest;
import com.sparta.project.dto.storerequest.StoreRequestAdminResponse;
import com.sparta.project.service.StoreRequestService;
import com.sparta.project.util.PermissionValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 4f6677d ([Feat] 가게 생성을 요청하는 기능)


@RestController
@RequiredArgsConstructor
@RequestMapping("/store-requests")
public class StoreRequestController {

<<<<<<< HEAD
<<<<<<< HEAD
    private final PermissionValidator permissionValidator;
=======
>>>>>>> 4f6677d ([Feat] 가게 생성을 요청하는 기능)
=======
    private final PermissionValidator permissionValidator;
>>>>>>> f50415c ([Feat] 가게 요청 승인 API)
    private final StoreRequestService storeRequestService;

    @PostMapping
    public ApiResponse<Void> createStoreRequest(Authentication authentication,
                                                @Valid @RequestBody StoreCreateRequest request) {
<<<<<<< HEAD
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
=======
>>>>>>> 4f6677d ([Feat] 가게 생성을 요청하는 기능)
        storeRequestService.createStoreRequest(Long.parseLong(authentication.getName()), request);
        return ApiResponse.success();
    }

<<<<<<< HEAD
<<<<<<< HEAD
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
=======
    @PostMapping("/{request_id}")
    public ApiResponse<Void> updateStoreRequest(Authentication authentication,
                                                @PathVariable String request_id) {
>>>>>>> f50415c ([Feat] 가게 요청 승인 API)
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        storeRequestService.approveStoreRequest(request_id);
        return ApiResponse.success();
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> eb1dc2e ([Feat] 음식점 생성 요청 반려 API)
    // 음식점 생성 요청 반려(MANAGER, MASTER)
    @DeleteMapping("/{request_id}")
    public ApiResponse<Void> rejectStoreRequest(Authentication authentication,
                                                @PathVariable String request_id,
                                                @NotBlank @RequestBody String rejectionReason) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        storeRequestService.rejectStoreRequest(Long.parseLong(authentication.getName()), request_id, rejectionReason);
        return ApiResponse.success();
    }
<<<<<<< HEAD

<<<<<<< HEAD
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

=======
>>>>>>> 66725a1 ([Feat] 음식점 생성 요청 목록 조회 API)
    // 음식점 생성 요청 목록 조회(MANAGER, MASTER)
    @GetMapping
    public ApiResponse<PageResponse<StoreRequestAdminResponse>> getAllStoreRequests(
            Authentication authentication,
<<<<<<< HEAD
            @PageableDefault(size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Direction.DESC)
            })
=======
            @PageableDefault(size = 5)
            @SortDefault(sort = "createdAt", direction = Direction.DESC)
>>>>>>> 66725a1 ([Feat] 음식점 생성 요청 목록 조회 API)
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

<<<<<<< HEAD
<<<<<<< HEAD
=======
import com.sparta.project.dto.storerequest.StoreRequestCreateRequest;
import com.sparta.project.dto.storerequest.StoreRequestResponse;
=======
import com.sparta.project.dto.StoreRequestCreateRequest;
import com.sparta.project.dto.StoreRequestResponse;
>>>>>>> d0a8147 ([Fix] controller 내 dto 경로 수정)
import com.sparta.project.service.StoreRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/store-requests")
public class StoreRequestController {

    private StoreRequestService storeRequestService;

    // 자신의 요청 목록 조회
    @GetMapping("/my")
    public List<StoreRequestResponse> getMyStoreRequests() {
        return storeRequestService.getMyStoreRequests();
    }

    // 음식점 생성 요청 상세 조회
    @GetMapping("/{request_id}")
    public StoreRequestResponse getStoreRequestById(@PathVariable String request_id) {
        return storeRequestService.getStoreRequestById(request_id);
    }

    // 음식점 생성 요청 목록 조회
    @GetMapping
    public List<StoreRequestResponse> getAllStoreRequests() {
        return storeRequestService.getAllStoreRequests();
    }

    // 음식점 생성 요청
    @PostMapping("/stores")
    public StoreRequestResponse createStoreRequest(@RequestBody StoreRequestCreateRequest request) {
        return storeRequestService.createStoreRequest(request);
    }

    // 음식점 생성 요청 승인
    @PostMapping("/{request_id}/approve")
    public StoreRequestResponse approveStoreRequest(@PathVariable String request_id) {
        return storeRequestService.approveStoreRequest(request_id);
    }

    // 음식점 생성 요청 반려
    @DeleteMapping("/{request_id}")
    public void deleteStoreRequest(@PathVariable String request_id) {
        storeRequestService.deleteStoreRequest(request_id);
    }
>>>>>>> c220516 ([Feat] : 컨트롤러 초안 작성)
}
=======
//package com.sparta.project.controller;
//
//import com.sparta.project.dto.storeRequest.StoreRequestRequest;
//import com.sparta.project.dto.storeRequest.StoreRequestResponse;
//import com.sparta.project.dto.common.ApiResponse;
//import com.sparta.project.dto.common.PageResponse;
//import com.sparta.project.service.StoreRequestService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/store-requests")
//public class StoreRequestController {
//
//    private final StoreRequestService storeRequestService;
=======
>>>>>>> 4f6677d ([Feat] 가게 생성을 요청하는 기능)
=======
//
//    // 음식점 생성 요청 반려(MANAGER, MASTER)
//    @DeleteMapping("/{request_id}")
//    public ApiResponse<Void> deleteStoreRequest(@PathVariable String request_id) {
//        storeRequestService.deleteStoreRequest(request_id);
//        return ApiResponse.success();
//    }
=======
>>>>>>> eb1dc2e ([Feat] 음식점 생성 요청 반려 API)

>>>>>>> f50415c ([Feat] 가게 요청 승인 API)
=======

>>>>>>> 66725a1 ([Feat] 음식점 생성 요청 목록 조회 API)
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
<<<<<<< HEAD
//    // 음식점 생성 요청 승인(MANAGER, MASTER)
//    @PostMapping("/{request_id}")
//    public ApiResponse<StoreRequestResponse> updateStoreRequest(
//            @PathVariable String request_id,
//            @RequestBody StoreRequestRequest storeRequestRequest) {
//        StoreRequestResponse approvedRequest = storeRequestService.updateStoreRequest(request_id, storeRequestRequest);
//        return ApiResponse.success(approvedRequest);
//    }
//
//    // 음식점 생성 요청 반려(MANAGER, MASTER)
//    @DeleteMapping("/{request_id}")
//    public ApiResponse<Void> deleteStoreRequest(@PathVariable String request_id) {
//        storeRequestService.deleteStoreRequest(request_id);
//        return ApiResponse.success();
//    }
<<<<<<< HEAD
//}
>>>>>>> 5f194e3 ([Fix] AI명세서대로 컨트롤러 초안 수정)
=======
=======

>>>>>>> f50415c ([Feat] 가게 요청 승인 API)
=======
>>>>>>> d5585a5 ([Feat] 음식점 생성 요청 정보 상세 조회 API)
}
>>>>>>> 4f6677d ([Feat] 가게 생성을 요청하는 기능)
