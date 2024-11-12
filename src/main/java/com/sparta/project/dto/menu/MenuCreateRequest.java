package com.sparta.project.dto.menu;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Valid
public record MenuCreateRequest(
        @NotBlank String storeId,
        @NotBlank String name,
        @NotBlank String description,
        @NotNull Integer price,
        @NotNull Boolean isClosed
) {}