package com.sparta.project.controller;

import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.storecategory.StoreCategoryCreateRequest;
import com.sparta.project.dto.storecategory.StoreCategoryResponse;
import com.sparta.project.dto.storecategory.StoreCategoryUpdateRequest;
import com.sparta.project.service.StoreCategoryService;
import com.sparta.project.util.PermissionValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class StoreCategoryController {

    private final PermissionValidator permissionValidator;
    private final StoreCategoryService storeCategoryService;


    @PostMapping
    public ApiResponse<Void> createStoreCategory(Authentication authentication,
                                                 @Valid @RequestBody StoreCategoryCreateRequest request) {
        permissionValidator.checkPermission(authentication, "MANAGER", "MASTER");
        storeCategoryService.createStoreCategory(request);
        return ApiResponse.success();
    }

    @PatchMapping("/{category_id}")
    public ApiResponse<Void> updateStoreCategory(Authentication authentication,
                                                 @PathVariable String category_id,
                                                 @NotNull @RequestBody StoreCategoryUpdateRequest request) {
        permissionValidator.checkPermission(authentication, "MANAGER", "MASTER");
        storeCategoryService.updateStoreCategory(category_id, request);
        return ApiResponse.success();
    }

    @DeleteMapping("/{category_id}")
    public ApiResponse<Void> deleteStoreCategory(Authentication authentication,
                                                 @PathVariable String category_id) {
        permissionValidator.checkPermission(authentication, "MANAGER", "MASTER");
        storeCategoryService.deleteStoreCategory(authentication.getName(), category_id);
        return ApiResponse.success();
    }

    // 음식점 카테고리 상세 조회(ALL)
    @GetMapping("/{category_id}")
    public ApiResponse<StoreCategoryResponse> getStoreCategoryById(Authentication authentication,
                                                                   @PathVariable String category_id) {
        StoreCategoryResponse response = storeCategoryService.getCategoryById(
                Long.parseLong(authentication.getName()), category_id
        );
        return ApiResponse.success(response);
    }

    // 음식점 카테고리 목록 조회(ALL)
    @GetMapping
    public ApiResponse<PageResponse<StoreCategoryResponse>> getAllStoreCategories(
            Authentication authentication,
            @PageableDefault(size = 5)
            @SortDefault(sort = "createdAt", direction = Direction.DESC)
            Pageable pageable,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "isDeleted", required = false) Boolean isDeleted) {
        Page<StoreCategoryResponse> result = storeCategoryService.getAllCategoriesBy(
                Long.parseLong(authentication.getName()), pageable, name, isDeleted
        );
        return ApiResponse.success(PageResponse.of(result));
    }

}
