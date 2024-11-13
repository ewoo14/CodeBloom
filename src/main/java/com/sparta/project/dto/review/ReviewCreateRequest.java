package com.sparta.project.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewCreateRequest (
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
        @NotNull Long userId,
>>>>>>> 0ebca46 ([Feat] 리뷰 생성 및 조회 구현)
=======
>>>>>>> 6241521 ([Fix] ReviewCreateRequest에서 userId 삭제)
        @NotBlank String storeId,
        @NotBlank String orderId,
        String content,
        @NotNull Integer score
<<<<<<< HEAD
=======
    @NotBlank String orderId,
    @NotBlank String storeId,
    String content,
    @NotNull Integer score
>>>>>>> df203c6 ([Feat] review dto 작성 완료)
=======
>>>>>>> 0ebca46 ([Feat] 리뷰 생성 및 조회 구현)
) {}