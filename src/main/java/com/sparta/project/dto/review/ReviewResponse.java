package com.sparta.project.dto.review;

import com.sparta.project.domain.Review;

import java.time.LocalDateTime;

public record ReviewResponse (
        String reviewId,
        String orderId,
        Long user_id,
        String storeId,
        String nickname,
        String content,
        Integer score,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {
    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getReviewId(),
                review.getOrder().getOrderId(),
                review.getUser().getUserId(),
                review.getStore().getStoreId(),
                review.getUser().getNickname(),
                review.getContent(),
                review.getScore(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
}
