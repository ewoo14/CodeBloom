<<<<<<< HEAD
package com.sparta.project.controller;

import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.storecategory.StoreCategoryCreateRequest;
import com.sparta.project.dto.storecategory.StoreCategoryResponse;
import com.sparta.project.dto.storecategory.StoreCategoryUpdateRequest;
import com.sparta.project.permission.PermissionValidator;
import com.sparta.project.service.StoreCategoryService;
import jakarta.validation.Valid;
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
            @PageableDefault(size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Direction.DESC)
            })
            Pageable pageable,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "isDeleted", required = false) Boolean isDeleted) {
        Page<StoreCategoryResponse> result = storeCategoryService.getAllCategoriesBy(
                Long.parseLong(authentication.getName()), pageable, name, isDeleted
        );
        return ApiResponse.success(PageResponse.of(result));
    }

}
=======
//package com.sparta.project.controller;
//
//import com.sparta.project.dto.storeCategory.StoreCategoryRequest;
//import com.sparta.project.dto.storeCategory.StoreCategoryResponse;
//import com.sparta.project.dto.common.ApiResponse;
//import com.sparta.project.dto.common.PageResponse;
//import com.sparta.project.service.StoreCategoryService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/categories")
//public class StoreCategoryController {
//
//    private final StoreCategoryService storeCategoryService;
//
//    // 음식점 카테고리 목록 조회(ALL)
//    @GetMapping
//    public ApiResponse<PageResponse<StoreCategoryResponse>> getAllStoreCategories(
//            @RequestParam("name") String name,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<StoreCategoryResponse> storeCategories = storeCategoryService.getAllStoreCategories(name, page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(storeCategories));
//    }
//
//    // 음식점 카테고리 상세 조회(ALL)
//    @GetMapping("/{category_id}")
//    public ApiResponse<StoreCategoryResponse> getStoreCategoryById(@PathVariable String category_id) {
//        StoreCategoryResponse storeCategory = storeCategoryService.getStoreCategoryById(category_id);
//        return ApiResponse.success(storeCategory);
//    }
//
//    // 음식점 카테고리 생성(MANAGER, MASTER)
//    @PostMapping
//    public ApiResponse<StoreCategoryResponse> createStoreCategory(@RequestBody StoreCategoryRequest storeCategoryRequest) {
//        StoreCategoryResponse madeStoreCategory = storeCategoryService.createStoreCategory(storeCategoryRequest);
//        return ApiResponse.success(madeStoreCategory);
//    }
//
//    // 음식점 카테고리 수정(MANAGER, MASTER)
//    @PatchMapping("/{category_id}")
//    public ApiResponse<StoreCategoryResponse> updateStoreCategory(
//            @PathVariable String category_id,
//            @RequestBody StoreCategoryRequest storeCategoryRequest) {
//        StoreCategoryResponse updatedStoreCategory = storeCategoryService.updateStoreCategory(category_id, storeCategoryRequest);
//        return ApiResponse.success(updatedStoreCategory);
//    }
//
//    // 음식점 카테고리 삭제(MANAGER, MASTER)
//    @DeleteMapping("/{category_id}")
//    public ApiResponse<Void> deleteStoreCategory(@PathVariable String category_id) {
//        storeCategoryService.deleteStoreCategory(category_id);
//        return ApiResponse.success();
//    }
//}
>>>>>>> 5f194e3 ([Fix] AI명세서대로 컨트롤러 초안 수정)
