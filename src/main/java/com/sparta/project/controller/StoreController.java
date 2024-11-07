package com.sparta.project.controller;

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

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;
    private final PermissionValidator permissionValidator;

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
