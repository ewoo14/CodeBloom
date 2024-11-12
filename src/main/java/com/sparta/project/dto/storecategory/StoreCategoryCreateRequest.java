package com.sparta.project.dto.storecategory;

import jakarta.validation.constraints.NotBlank;

public record StoreCategoryCreateRequest(
        @NotBlank String name,
        @NotBlank String description
) {
}
