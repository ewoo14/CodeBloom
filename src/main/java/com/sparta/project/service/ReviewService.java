package com.sparta.project.service;

import com.sparta.project.domain.*;
import com.sparta.project.dto.review.ReviewCreateRequest;
import com.sparta.project.dto.review.ReviewUpdateRequest;
import com.sparta.project.dto.review.ReviewResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.OrderRepository;
import com.sparta.project.repository.ReviewRepository;
import com.sparta.project.repository.StoreRepository;
import com.sparta.project.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    private static final Logger log = LoggerFactory.getLogger(ReviewService.class);

    // 리뷰 상세 조회
    @Transactional(readOnly = true)
    public ReviewResponse getReviewById(String reviewId) {
        Review review = getReviewOrException(reviewId);
        return ReviewResponse.from(review);
    }

    // 내 리뷰 목록 조회
    @Transactional(readOnly = true)
    public Page<ReviewResponse> getMyReviews(Long userId, int page, int size) {
        Sort sort = Sort.by(Sort.Order.desc("createdAt"), Sort.Order.desc("updatedAt"));
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        QReview qReview = QReview.review;
        BooleanExpression predicate = qReview.user.userId.eq(userId);
        return reviewRepository.findAll(predicate, pageable)
                .map(ReviewResponse::from);
    }

    // 가게 리뷰 목록 조회
    @Transactional(readOnly = true)
    public Page<ReviewResponse> getReviewsByStore(String storeId, int page, int size) {
        Sort sort = Sort.by(Sort.Order.desc("createdAt"), Sort.Order.desc("updatedAt"));
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        QReview qReview = QReview.review;
        BooleanExpression predicate = qReview.store.storeId.eq(storeId);
        return reviewRepository.findAll(predicate, pageable)
                .map(ReviewResponse::from);
    }

    // 리뷰 작성
    @Transactional
    public String createReview(Long userId, ReviewCreateRequest reviewCreateRequest) {
        log.info("리뷰 생성 요청 전 storeId: {} , orderId: {}",
                reviewCreateRequest.storeId(), reviewCreateRequest.orderId());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.USER_NOT_FOUND));
        Store store = storeRepository.findById(reviewCreateRequest.storeId())
                .orElseThrow(() -> new CodeBloomException(ErrorCode.STORE_NOT_FOUND));
        Order order = orderRepository.findById(reviewCreateRequest.orderId())
                .orElseThrow(() -> new CodeBloomException(ErrorCode.ORDER_NOT_FOUND));

        Review review = Review.create(
                        user,
                        store,
                        order,
                        reviewCreateRequest.content(),
                        reviewCreateRequest.score()
                );

        reviewRepository.save(review);
        log.info("리뷰 생성 ID: {}", review.getReviewId());
        return review.getReviewId();
    }

    // 리뷰 수정
    @Transactional
    public String updateReview(String reviewId,ReviewUpdateRequest reviewUpdateRequest) {
        Review review = getReviewOrException(reviewId);

        review.update(
                reviewUpdateRequest.content(),
                reviewUpdateRequest.score()
        );
        reviewRepository.save(review);
        return review.getReviewId();
    }

    // 리뷰 삭제
    @Transactional
    public void deleteReview(String reviewId, Authentication authentication) {
        Review review = getReviewOrException(reviewId);
        review.deleteBase(authentication.getName());
    }

    // review_id 공통 활용
    private Review getReviewOrException(String reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.REVIEW_NOT_FOUND));
    }
}