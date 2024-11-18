package com.sparta.project.controller;

import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.review.ReviewCreateRequest;
import com.sparta.project.dto.review.ReviewResponse;
import com.sparta.project.dto.review.ReviewUpdateRequest;
import com.sparta.project.permission.PermissionValidator;
import com.sparta.project.service.ReviewService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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
            @PageableDefault(size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            }) Pageable pageable,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        Page<ReviewResponse> myReviews = reviewService.getMyReviews(userId, pageable);
        return ApiResponse.success(PageResponse.of(myReviews));
    }

    // 가게 리뷰 목록 조회(ALL)
    @GetMapping
    public ApiResponse<PageResponse<ReviewResponse>> getAllReviewsByStore(
            @RequestParam String storeId,
            @PageableDefault(size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            }) Pageable pageable) {
        Page<ReviewResponse> storeReviews = reviewService.getReviewsByStore(storeId, pageable);
        return ApiResponse.success(PageResponse.of(storeReviews));
    }

    // 리뷰 작성(CUSTOMER)
    @PostMapping
    public ApiResponse<String> createReview(
            @Valid @RequestBody ReviewCreateRequest reviewCreateRequest,
            Authentication authentication) {
        log.info("리뷰 생성 요청 수신: {}", reviewCreateRequest);
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
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
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        String updatedReview = reviewService.updateReview(review_id, reviewUpdateRequest);
        return ApiResponse.success(updatedReview);
    }

    // 리뷰 삭제(CUSTOMER)
    @DeleteMapping("/{review_id}")
    public ApiResponse<Void> deleteReview(
            @PathVariable String review_id,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        reviewService.deleteReview(review_id, authentication);
        return ApiResponse.success();
    }
}
