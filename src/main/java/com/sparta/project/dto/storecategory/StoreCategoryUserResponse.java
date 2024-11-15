package com.sparta.project.dto.storecategory;

import com.sparta.project.domain.StoreCategory;
import java.time.LocalDateTime;

public record StoreCategoryUserResponse(
        String storeCategoryId,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) implements StoreCategoryResponse {

    public static StoreCategoryUserResponse from(StoreCategory storeCategory) {
        return new StoreCategoryUserResponse(
                storeCategory.getStoreCategoryId(),
                storeCategory.getName(),
                storeCategory.getDescription(),
                storeCategory.getCreatedAt(),
                storeCategory.getUpdatedAt()
        );
    }
}
