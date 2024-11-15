package com.sparta.project.dto.storerequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.project.domain.StoreRequest;
import com.sparta.project.domain.enums.StoreRequestStatus;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StoreRequestUserResponse(
        String storeRequestId,
        StoreRequestStatus status,
        String name,
        String description,
        String rejectionReason,
        String storeCategoryId,
        String locationId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime rejectedAt
) {
    public static StoreRequestUserResponse from(StoreRequest storeRequest) {
        return new StoreRequestUserResponse(
                storeRequest.getStoreRequestId(),
                storeRequest.getStatus(),
                storeRequest.getName(),
                storeRequest.getDescription(),
                storeRequest.getRejectionReason(),
                storeRequest.getStoreCategory().getStoreCategoryId(),
                storeRequest.getLocation().getLocationId(),
                storeRequest.getCreatedAt(),
                storeRequest.getUpdatedAt(),
                storeRequest.getDeletedAt()
        );
    }
}
