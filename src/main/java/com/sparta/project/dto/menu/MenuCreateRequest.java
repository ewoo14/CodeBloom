package com.sparta.project.dto.menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MenuCreateRequest(
        @NotBlank String storeId,
        @NotBlank String name,
        String description,
        @NotNull Integer price,
        @NotNull Boolean isClosed
) {}