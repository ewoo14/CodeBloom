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
//
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
//    // 음식점 목록 조회(ALL)
//    @GetMapping
//    public ApiResponse<PageResponse<StoreResponse>> getAllStores(
//            @RequestParam("name") String name,
//            @RequestParam("categoryId") String categoryId,
//            @RequestParam("menu") String menu,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<StoreResponse> stores = storeService.getAllStores(name, categoryId, menu, page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(stores));
//    }
//
//    // 음식점 상세 조회(ALL)
//    @GetMapping("/{store_id}")
//    public ApiResponse<StoreResponse> getStoreById(@PathVariable String store_id) {
//        StoreResponse store = storeService.getStoreById(store_id);
//        return ApiResponse.success(store);
//    }
//
//    // 음식점 정보 수정(OWNER, MANAGER, MASTER)
//    @PatchMapping("/{store_id}")
//    public ApiResponse<StoreResponse> updateStore(
//            @PathVariable String store_id,
//            @RequestBody StoreRequest storeRequest) {
//        StoreResponse updatedStore = storeService.updateStore(store_id, storeRequest);
//        return ApiResponse.success(updatedStore);
//    }
//
//    // 음식점 삭제(OWNER, MANAGER, MASTER)
//    @DeleteMapping("/{store_id}")
//    public ApiResponse<Void> deleteStore(@PathVariable String store_id) {
//        storeService.deleteStore(store_id);
//        return ApiResponse.success();
//    }
//}
