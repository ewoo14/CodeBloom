<<<<<<< HEAD
<<<<<<< HEAD
package com.sparta.project.controller;

<<<<<<< HEAD
<<<<<<< HEAD

import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.store.StoreResponse;
import com.sparta.project.dto.store.StoreUpdateRequest;
import com.sparta.project.permission.PermissionValidator;
import com.sparta.project.service.StoreService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
=======
package com.sparta.project.controller;


import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.store.StoreResponse;
import com.sparta.project.dto.store.StoreUpdateRequest;
import com.sparta.project.service.StoreService;
import com.sparta.project.util.PermissionValidator;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 552931a ([Feat] 음식점 상세 조회 완료)
=======
=======
import org.springframework.data.domain.Page;
>>>>>>> ecac4ce ([Feat] 음식점 목록 조회 기능)
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
>>>>>>> 5bd2e4b ([Feat] 음식점 정보 수정 완료)

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;
<<<<<<< HEAD
<<<<<<< HEAD
    private final PermissionValidator permissionValidator;

<<<<<<< HEAD
    // 자신의 음식점 조회(OWNER)
    @GetMapping("/my")
    public ApiResponse<PageResponse<StoreResponse>> getMyStores(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
        Page<StoreResponse> myStores = storeService.getMyStores(page, size, Long.parseLong(authentication.getName()));
        return ApiResponse.success(PageResponse.of(myStores));
    }

    // 음식점 목록 조회(ALL)
    @GetMapping
    public ApiResponse<PageResponse<StoreResponse>> getAllStores(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "categoryId", required = false) String categoryId,
            @RequestParam(value = "menu", required = false) String menu,
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        Page<StoreResponse> stores = storeService.getAllStores(name, categoryId, menu, pageable);
        return ApiResponse.success(PageResponse.of(stores));
    }

    // 음식점 상세 조회(ALL)
    @GetMapping("/{store_id}")
    public ApiResponse<StoreResponse> getStoreById(@PathVariable("store_id") String store_id) {
        StoreResponse store = storeService.getStoreById(store_id);
        return ApiResponse.success(store);
    }

    // 음식점 정보 수정(OWNER, MANAGER, MASTER)
    @PatchMapping("/{store_id}")
    public ApiResponse<String> updateStore(
            @PathVariable("store_id") String store_id,
            @RequestBody @NotNull StoreUpdateRequest storeUpdateRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "OWNER", "MANAGER", "MASTER");
        String storeId = storeService.updateStore(store_id, storeUpdateRequest);
        return ApiResponse.success(storeId);
    }

    // 음식점 삭제(OWNER, MANAGER, MASTER)
    @DeleteMapping("/{store_id}")
    public ApiResponse<Void> deleteStore(@PathVariable("store_id") String store_id, Authentication authentication) {
        permissionValidator.checkPermission(authentication, "OWNER", "MANAGER", "MASTER");
        storeService.deleteStore(store_id, Long.parseLong(authentication.getName()));
        return ApiResponse.success();
=======
import com.sparta.project.dto.store.StoreUpdateRequest;
import com.sparta.project.dto.store.StoreResponse;
=======
import com.sparta.project.dto.StoreUpdateRequest;
import com.sparta.project.dto.StoreResponse;
>>>>>>> d0a8147 ([Fix] controller 내 dto 경로 수정)
import com.sparta.project.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private StoreService storeService;

    // 자신의 음식점 조회
    @GetMapping("/my")
    public List<StoreResponse> getMyStores() {
        return storeService.getMyStores();
    }

    // 음식점 목록 조회
    @GetMapping
    public List<StoreResponse> getAllStores(@RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer size,
                                            @RequestParam(required = false) String sortBy) {
        return storeService.findAllStores(page, size, sortBy);
    }

    // 음식점 상세 조회
    @GetMapping("/{store_id}")
    public StoreResponse getStoreById(@PathVariable String store_id) {
        return storeService.getStoreById(store_id);
    }

    // 음식점 검색
    @GetMapping("/search")
    public List<StoreResponse> searchStores(@RequestParam(required = false) String name,
                                            @RequestParam(required = false) String categoryId,
                                            @RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer size,
                                            @RequestParam(required = false) String sortBy) {
        return storeService.searchStores(name, categoryId, page, size, sortBy);
    }

    // 음식점 정보 수정
    @PatchMapping("/{store_id}")
    public StoreResponse updateStore(@PathVariable String store_id, @RequestBody StoreUpdateRequest updateRequest) {
        return storeService.updateStore(store_id, updateRequest);
    }

    // 음식점 삭제
    @DeleteMapping("/{store_id}")
    public void deleteStore(@PathVariable String store_id) {
        storeService.deleteStore(store_id);
>>>>>>> c220516 ([Feat] : 컨트롤러 초안 작성)
    }
}
=======
//package com.sparta.project.controller;
//
//import com.sparta.project.dto.store.StoreRequest;
//import com.sparta.project.dto.store.StoreResponse;
//import com.sparta.project.dto.common.ApiResponse;
//import com.sparta.project.dto.common.PageResponse;
//import com.sparta.project.service.StoreService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/stores")
//public class StoreController {
//
//    private final StoreService storeService;
=======
    private PermissionValidator permissionValidator;
<<<<<<< HEAD
>>>>>>> 552931a ([Feat] 음식점 상세 조회 완료)
//
=======
=======
    private final PermissionValidator permissionValidator;
>>>>>>> c80f876 ([Feat] 음식점 정보 삭제 완료)

    //
>>>>>>> 5bd2e4b ([Feat] 음식점 정보 수정 완료)
//    // 자신의 음식점 조회(OWNER)
//    @GetMapping("/my")
//    public ApiResponse<PageResponse<StoreResponse>> getMyStores(
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<StoreResponse> myStores = storeService.getMyStores(page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(myStores));
//    }
//
=======

    // 자신의 음식점 조회(OWNER)
    @GetMapping("/my")
    public ApiResponse<PageResponse<StoreResponse>> getMyStores(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
        Page<StoreResponse> myStores = storeService.getMyStores(page, size, Long.parseLong(authentication.getName()));
        return ApiResponse.success(PageResponse.of(myStores));
    }

>>>>>>> 580d264 ([Feat] 자신의 가게 목록 조회)
    // 음식점 목록 조회(ALL)
    @GetMapping
    public ApiResponse<PageResponse<StoreResponse>> getAllStores(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "categoryId", required = false) String categoryId,
            @RequestParam(value = "menu", required = false) String menu,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        Page<StoreResponse> stores = storeService.getAllStores(name, categoryId, menu, page, size);
        return ApiResponse.success(PageResponse.of(stores));
    }

    // 음식점 상세 조회(ALL)
    @GetMapping("/{store_id}")
    public ApiResponse<StoreResponse> getStoreById(@PathVariable("store_id") String store_id) {
        StoreResponse store = storeService.getStoreById(store_id);
        return ApiResponse.success(store);
    }

    // 음식점 정보 수정(OWNER, MANAGER, MASTER)
    @PatchMapping("/{store_id}")
    public ApiResponse<String> updateStore(
            @PathVariable("store_id") String store_id,
            @RequestBody @NotNull StoreUpdateRequest storeUpdateRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "OWNER", "MANAGER", "MASTER");
        String storeId = storeService.updateStore(store_id, storeUpdateRequest);
        return ApiResponse.success(storeId);
    }
<<<<<<< HEAD
//
//    // 음식점 삭제(OWNER, MANAGER, MASTER)
//    @DeleteMapping("/{store_id}")
//    public ApiResponse<Void> deleteStore(@PathVariable String store_id) {
//        storeService.deleteStore(store_id);
//        return ApiResponse.success();
//    }
<<<<<<< HEAD
//}
>>>>>>> 5f194e3 ([Fix] AI명세서대로 컨트롤러 초안 수정)
=======
=======

    // 음식점 삭제(OWNER, MANAGER, MASTER)
    @DeleteMapping("/{store_id}")
    public ApiResponse<Void> deleteStore(@PathVariable("store_id") String store_id, Authentication authentication) {
        permissionValidator.checkPermission(authentication, "OWNER", "MANAGER", "MASTER");
        storeService.deleteStore(store_id, Long.parseLong(authentication.getName()));
        return ApiResponse.success();
    }
>>>>>>> c80f876 ([Feat] 음식점 정보 삭제 완료)
}
>>>>>>> 552931a ([Feat] 음식점 상세 조회 완료)
