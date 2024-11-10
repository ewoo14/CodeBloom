package com.sparta.project.dto.storerequest;

import jakarta.validation.constraints.NotBlank;

public record StoreCreateRequest(
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String address,
        @NotBlank String storeCategoryId,
        @NotBlank String locationId
) {
}
