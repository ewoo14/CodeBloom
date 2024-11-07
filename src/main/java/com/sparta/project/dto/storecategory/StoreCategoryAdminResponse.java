package com.sparta.project.dto.storecategory;

import com.sparta.project.domain.StoreCategory;
import java.time.LocalDateTime;

public record StoreCategoryAdminResponse(
        String storeCategoryId,
        String name,
        String description,
        Boolean isDeleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        LocalDateTime deletedAt,
        String deletedBy
) implements StoreCategoryResponse {

    public static StoreCategoryAdminResponse from(StoreCategory storeCategory) {
        return new StoreCategoryAdminResponse(
                storeCategory.getStoreCategoryId(),
                storeCategory.getName(),
                storeCategory.getDescription(),
                storeCategory.getIsDeleted(),
                storeCategory.getCreatedAt(),
                storeCategory.getCreatedBy(),
                storeCategory.getUpdatedAt(),
                storeCategory.getUpdatedBy(),
                storeCategory.getDeletedAt(),
                storeCategory.getDeletedBy()
        );
    }
}
