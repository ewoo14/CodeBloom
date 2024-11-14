package com.sparta.project.controller;

import com.sparta.project.dto.review.ReviewCreateRequest;
import com.sparta.project.dto.review.ReviewUpdateRequest;
import com.sparta.project.dto.review.ReviewResponse;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.service.ReviewService;
import com.sparta.project.util.PermissionValidator;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final PermissionValidator permissionValidator;
    private static final Logger log = LoggerFactory.getLogger(ReviewController.class);

    // 리뷰 상세 조회(ALL)
    @GetMapping("/{review_id}")
    public ApiResponse<ReviewResponse> getReviewById(@PathVariable String review_id) {
        ReviewResponse review = reviewService.getReviewById(review_id);
        return ApiResponse.success(review);
    }

    // 내 리뷰 목록 조회(CUSTOMER)
    @GetMapping("/my")
    public ApiResponse<PageResponse<ReviewResponse>> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "userId") String sortBy,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "CUSTOMER");
        Page<ReviewResponse> myReviews = reviewService.getMyReviews(userId, page, size, sortBy);
        return ApiResponse.success(PageResponse.of(myReviews));
    }

    // 가게 리뷰 목록 조회(ALL)
    @GetMapping
    public ApiResponse<PageResponse<ReviewResponse>> getAllReviewsByStore(
            @RequestParam String storeId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "storeId") String sortBy) {
        Page<ReviewResponse> storeReviews = reviewService.getReviewsByStore(storeId, page, size, sortBy);
        return ApiResponse.success(PageResponse.of(storeReviews));
    }

    // 리뷰 작성(CUSTOMER)
    @PostMapping
    public ApiResponse<String> createReview(
            @Valid @RequestBody ReviewCreateRequest reviewCreateRequest,
            Authentication authentication) {
        log.info("리뷰 생성 요청 수신: {}", reviewCreateRequest);
        permissionValidator.checkPermission(authentication, "CUSTOMER");
        Long userId = Long.parseLong(authentication.getName());
        String review = reviewService.createReview(userId, reviewCreateRequest);
        return ApiResponse.success(review);
    }

    // 리뷰 수정(CUSTOMER)
    @PatchMapping("/{review_id}")
    public ApiResponse<String> updateReview(
            @PathVariable String review_id,
            @NotNull @RequestBody ReviewUpdateRequest reviewUpdateRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "CUSTOMER");
        String updatedReview = reviewService.updateReview(review_id, reviewUpdateRequest);
        return ApiResponse.success(updatedReview);
    }

    // 리뷰 삭제(CUSTOMER)
    @DeleteMapping("/{review_id}")
    public ApiResponse<Void> deleteReview(
            @PathVariable String review_id,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "CUSTOMER");
        reviewService.deleteReview(review_id, authentication);
        return ApiResponse.success();
    }
}
