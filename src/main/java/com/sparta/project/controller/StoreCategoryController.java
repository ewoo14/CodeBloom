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
