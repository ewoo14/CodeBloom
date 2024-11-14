package com.sparta.project.dto.storerequest;


import com.sparta.project.domain.StoreRequest;
import com.sparta.project.domain.enums.StoreRequestStatus;
import java.time.LocalDateTime;

public record StoreRequestAdminResponse(
        String storeRequestId,
        StoreRequestStatus status,
        String name,
        String description,
        String rejectionReason,
        String storeCategoryId,
        String locationId,
        Long ownerId,
        Boolean isDeleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        LocalDateTime deletedAt,
        String deletedBy
) {
    public static StoreRequestAdminResponse from(final StoreRequest storeRequest) {
        return new StoreRequestAdminResponse(
                storeRequest.getStoreRequestId(),
                storeRequest.getStatus(),
                storeRequest.getName(),
                storeRequest.getDescription(),
                storeRequest.getRejectionReason(),
                storeRequest.getStoreCategory().getStoreCategoryId(),
                storeRequest.getLocation().getLocationId(),
                storeRequest.getOwner().getUserId(),
                storeRequest.getIsDeleted(),
                storeRequest.getCreatedAt(),
                storeRequest.getCreatedBy(),
                storeRequest.getUpdatedAt(),
                storeRequest.getUpdatedBy(),
                storeRequest.getDeletedAt(),
                storeRequest.getDeletedBy()
        );
    }
}