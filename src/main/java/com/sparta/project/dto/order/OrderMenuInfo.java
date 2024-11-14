package com.sparta.project.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderMenuInfo(
        @NotBlank String menuId,
        @NotNull @Positive Integer count
) {
}
