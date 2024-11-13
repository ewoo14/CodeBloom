package com.sparta.project.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewCreateRequest (
<<<<<<< HEAD
        @NotBlank String storeId,
        @NotBlank String orderId,
        String content,
        @NotNull Integer score
=======
    @NotBlank String orderId,
    @NotBlank String storeId,
    String content,
    @NotNull Integer score
>>>>>>> df203c6 ([Feat] review dto 작성 완료)
) {}