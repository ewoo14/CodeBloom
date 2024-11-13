package com.sparta.project.dto.review;

import com.sparta.project.domain.Review;

import java.time.LocalDateTime;
<<<<<<< HEAD
<<<<<<< HEAD
import java.time.format.DateTimeFormatter;
=======
>>>>>>> df203c6 ([Feat] review dto 작성 완료)
=======
import java.time.format.DateTimeFormatter;
>>>>>>> 953ad15 ([Fix] DateTime을 String으로 포맷)

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
