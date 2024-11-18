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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
            @PageableDefault(size = 5)
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
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
    }
}
