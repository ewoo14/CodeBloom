<<<<<<< HEAD
<<<<<<< HEAD
package com.sparta.project.controller;

<<<<<<< HEAD
<<<<<<< HEAD
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
=======
package com.sparta.project.controller;

import com.sparta.project.domain.enums.Role;
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
>>>>>>> 426a1a2 ([Fix] Location, Review 권한 조정)
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final PermissionValidator permissionValidator;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 0ebca46 ([Feat] 리뷰 생성 및 조회 구현)
    private static final Logger log = LoggerFactory.getLogger(ReviewController.class);

    // 리뷰 상세 조회(ALL)
    @GetMapping("/{review_id}")
    public ApiResponse<ReviewResponse> getReviewById(@PathVariable String review_id) {
=======

    // 리뷰 상세 조회(ALL)
    @GetMapping("/{review_id}")
<<<<<<< HEAD
    public ApiResponse<ReviewResponse> getReviewById(@PathVariable Long review_id) {
>>>>>>> 426a1a2 ([Fix] Location, Review 권한 조정)
=======
    public ApiResponse<ReviewResponse> getReviewById(@PathVariable String review_id) {
>>>>>>> 7b0efcd ([Fix] 컨트롤러 Valid 어노테이션 추가 및 review_id String 변경)
        ReviewResponse review = reviewService.getReviewById(review_id);
        return ApiResponse.success(review);
    }

    // 내 리뷰 목록 조회(CUSTOMER)
    @GetMapping("/my")
    public ApiResponse<PageResponse<ReviewResponse>> getMyReviews(
            @RequestParam Long userId,
<<<<<<< HEAD
<<<<<<< HEAD
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            }) Pageable pageable,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        Page<ReviewResponse> myReviews = reviewService.getMyReviews(userId, pageable);
=======
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
=======
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "userId") String sortBy,
>>>>>>> 94ce7b1 ([Fix] 각 컨트롤러 RequestParam 조건 추가)
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        Page<ReviewResponse> myReviews = reviewService.getMyReviews(userId, page, size, sortBy);
>>>>>>> 426a1a2 ([Fix] Location, Review 권한 조정)
        return ApiResponse.success(PageResponse.of(myReviews));
    }

    // 가게 리뷰 목록 조회(ALL)
    @GetMapping
    public ApiResponse<PageResponse<ReviewResponse>> getAllReviewsByStore(
            @RequestParam String storeId,
<<<<<<< HEAD
<<<<<<< HEAD
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            }) Pageable pageable) {
        Page<ReviewResponse> storeReviews = reviewService.getReviewsByStore(storeId, pageable);
=======
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy) {
=======
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "storeId") String sortBy) {
>>>>>>> 94ce7b1 ([Fix] 각 컨트롤러 RequestParam 조건 추가)
        Page<ReviewResponse> storeReviews = reviewService.getReviewsByStore(storeId, page, size, sortBy);
>>>>>>> 426a1a2 ([Fix] Location, Review 권한 조정)
        return ApiResponse.success(PageResponse.of(storeReviews));
    }

    // 리뷰 작성(CUSTOMER)
    @PostMapping
<<<<<<< HEAD
<<<<<<< HEAD
    public ApiResponse<String> createReview(
            @Valid @RequestBody ReviewCreateRequest reviewCreateRequest,
            Authentication authentication) {
        log.info("리뷰 생성 요청 수신: {}", reviewCreateRequest);
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        Long userId = Long.parseLong(authentication.getName());
        String review = reviewService.createReview(userId, reviewCreateRequest);
=======
    public ApiResponse<ReviewResponse> createReview(
=======
    public ApiResponse<String> createReview(
>>>>>>> ff9030a ([Feat] review 수정, 삭제 추가 및 생성과 수정 반환 형식 id만으로 변경)
            @Valid @RequestBody ReviewCreateRequest reviewCreateRequest,
            Authentication authentication) {
        log.info("리뷰 생성 요청 수신: {}", reviewCreateRequest);
        permissionValidator.checkPermission(authentication, "CUSTOMER");
<<<<<<< HEAD
<<<<<<< HEAD
        ReviewResponse review = reviewService.createReview(reviewCreateRequest);
>>>>>>> 426a1a2 ([Fix] Location, Review 권한 조정)
        return ApiResponse.success(review);
    }

<<<<<<< HEAD
    // 리뷰 수정(CUSTOMER)
    @PatchMapping("/{review_id}")
<<<<<<< HEAD
    public ApiResponse<String> updateReview(
            @PathVariable String review_id,
            @NotNull @RequestBody ReviewUpdateRequest reviewUpdateRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        String updatedReview = reviewService.updateReview(review_id, reviewUpdateRequest);
=======
    public ApiResponse<ReviewResponse> updateReview(
            @PathVariable String review_id,
            @RequestBody ReviewUpdateRequest reviewUpdateRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "CUSTOMER");
        ReviewResponse updatedReview = reviewService.updateReview(review_id, reviewUpdateRequest);
>>>>>>> 426a1a2 ([Fix] Location, Review 권한 조정)
=======
        String review = reviewService.createReview(reviewCreateRequest);
=======
        Long userId = Long.parseLong(authentication.getName());
        String review = reviewService.createReview(userId, reviewCreateRequest);
>>>>>>> 6241521 ([Fix] ReviewCreateRequest에서 userId 삭제)
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
>>>>>>> ff9030a ([Feat] review 수정, 삭제 추가 및 생성과 수정 반환 형식 id만으로 변경)
        return ApiResponse.success(updatedReview);
    }

    // 리뷰 삭제(CUSTOMER)
    @DeleteMapping("/{review_id}")
    public ApiResponse<Void> deleteReview(
            @PathVariable String review_id,
            Authentication authentication) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        reviewService.deleteReview(review_id, authentication);
        return ApiResponse.success();
=======
import com.sparta.project.dto.review.ReviewRequest;
import com.sparta.project.dto.review.ReviewResponse;
=======
import com.sparta.project.dto.ReviewRequest;
import com.sparta.project.dto.ReviewResponse;
>>>>>>> d0a8147 ([Fix] controller 내 dto 경로 수정)
import com.sparta.project.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    // 리뷰 상세 조회
    @GetMapping("/{review_id}")
    public ReviewResponse getReviewById(@PathVariable String review_id) {
        return reviewService.getReviewById(review_id);
    }

    // 내 리뷰 목록 조회
    @GetMapping("/my")
    public List<ReviewResponse> getMyReviews() {
        return reviewService.getMyReviews();
    }

    // 가게 리뷰 목록 조회
    @GetMapping("/stores/{store_id}")
    public List<ReviewResponse> getReviewsByStore(@PathVariable String store_id) {
        return reviewService.getReviewsByStore(store_id);
    }

    // 리뷰 작성
    @PostMapping
    public ReviewResponse addReview(@RequestBody ReviewRequest reviewRequest) {
        return reviewService.addReview(reviewRequest);
    }

    // 리뷰 수정
    @PatchMapping("/{review_id}")
    public ReviewResponse updateReview(@PathVariable String review_id, @RequestBody ReviewRequest reviewRequest) {
        return reviewService.updateReview(review_id, reviewRequest);
    }

    // 리뷰 삭제
    @DeleteMapping("/{review_id}")
    public void deleteReview(@PathVariable String review_id) {
        reviewService.deleteReview(review_id);
>>>>>>> c220516 ([Feat] : 컨트롤러 초안 작성)
    }
}
=======
//package com.sparta.project.controller;
//
//import com.sparta.project.dto.review.ReviewRequest;
//import com.sparta.project.dto.review.ReviewResponse;
//import com.sparta.project.dto.common.ApiResponse;
//import com.sparta.project.dto.common.PageResponse;
//import com.sparta.project.service.ReviewService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/reviews")
//public class ReviewController {
//
//    private final ReviewService reviewService;
//
//    // 리뷰 상세 조회(ALL)
//    @GetMapping("/{review_id}")
//    public ApiResponse<ReviewResponse> getReviewById(@PathVariable Long review_id) {
//        ReviewResponse review = reviewService.getReviewById(review_id);
//        return ApiResponse.success(review);
//    }
//
//    // 내 리뷰 목록 조회(CUSTOMER)
//    @GetMapping("/my")
//    public ApiResponse<PageResponse<ReviewResponse>> getMyReviews(
//            @RequestParam Long userId,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<ReviewResponse> myReviews = reviewService.getMyReviews(userId, page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(myReviews));
//    }
//
//    // 가게 리뷰 목록 조회(ALL)
//    @GetMapping
//    public ApiResponse<PageResponse<ReviewResponse>> getAllReviewsByStore(
//            @RequestParam String storeId,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<ReviewResponse> storeReviews = reviewService.getReviewsByStore(storeId, page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(storeReviews));
//    }
//
//    // 리뷰 작성(CUSTOMER)
//    @PostMapping
//    public ApiResponse<ReviewResponse> createReview(@RequestBody ReviewRequest reviewRequest) {
//        ReviewResponse review = reviewService.createReview(reviewRequest);
//        return ApiResponse.success(review);
//    }
//
=======
>>>>>>> 0ebca46 ([Feat] 리뷰 생성 및 조회 구현)
//    // 리뷰 수정(CUSTOMER)
//    @PatchMapping("/{review_id}")
//    public ApiResponse<ReviewResponse> updateReview(
//            @PathVariable String review_id,
<<<<<<< HEAD
//            @RequestBody ReviewRequest reviewRequest) {
//        ReviewResponse updatedReview = reviewService.updateReview(review_id, reviewRequest);
=======
//            @RequestBody ReviewUpdateRequest reviewUpdateRequest,
//            Authentication authentication) {
//        permissionValidator.checkPermission(authentication, "CUSTOMER");
//        ReviewResponse updatedReview = reviewService.updateReview(review_id, reviewUpdateRequest);
>>>>>>> 0ebca46 ([Feat] 리뷰 생성 및 조회 구현)
//        return ApiResponse.success(updatedReview);
//    }
//
//    // 리뷰 삭제(CUSTOMER)
//    @DeleteMapping("/{review_id}")
<<<<<<< HEAD
//    public ApiResponse<Void> deleteReview(@PathVariable String review_id) {
//        reviewService.deleteReview(review_id);
//        return ApiResponse.success();
//    }
//}
>>>>>>> 5f194e3 ([Fix] AI명세서대로 컨트롤러 초안 수정)
=======
        permissionValidator.checkPermission(authentication, "CUSTOMER");
        reviewService.deleteReview(review_id);
        return ApiResponse.success();
    }
=======
//    public ApiResponse<Void> deleteReview(
//            @PathVariable String review_id,
//            Authentication authentication) {
//        permissionValidator.checkPermission(authentication, "CUSTOMER");
//        reviewService.deleteReview(review_id);
//        return ApiResponse.success();
//    }
>>>>>>> 0ebca46 ([Feat] 리뷰 생성 및 조회 구현)
=======
        permissionValidator.checkPermission(authentication, "CUSTOMER");
=======
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
>>>>>>> 06c83a4 ([Refactor] 권한 설정시 Role 객체 사용으로 변경)
        reviewService.deleteReview(review_id, authentication);
        return ApiResponse.success();
    }
>>>>>>> ff9030a ([Feat] review 수정, 삭제 추가 및 생성과 수정 반환 형식 id만으로 변경)
}
>>>>>>> 426a1a2 ([Fix] Location, Review 권한 조정)
