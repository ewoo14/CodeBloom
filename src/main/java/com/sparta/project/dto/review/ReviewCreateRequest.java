package com.sparta.project.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewCreateRequest (
        @NotNull Long userId,
        @NotBlank String storeId,
        @NotBlank String orderId,
        String content,
        @NotNull Integer score
) {}