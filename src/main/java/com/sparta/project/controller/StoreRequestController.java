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
//    // 음식점 생성 요청(OWNER)
//    @PostMapping
//    public ApiResponse<StoreRequestResponse> createStoreRequest(@RequestBody StoreRequestRequest storeRequestRequest) {
//        StoreRequestResponse madeRequest = storeRequestService.createStoreRequest(storeRequestRequest);
//        return ApiResponse.success(madeRequest);
//    }
//
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
//}
