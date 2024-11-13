package com.sparta.project.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewCreateRequest (
    @NotBlank String orderId,
    @NotBlank String storeId,
    String content,
    @NotNull Integer score
) {}