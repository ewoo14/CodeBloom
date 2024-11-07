package com.sparta.project.dto.storerequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.project.domain.StoreRequest;
import com.sparta.project.domain.enums.StoreRequestStatus;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StoreRequestOwnerResponse(
        String storeRequestId,
        String name,
        String description,
        String storeCategoryId,
        String locationId,
        StoreRequestStatus status,
        String rejectionReason,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime rejectedAt
) implements StoreRequestResponse {
    public static StoreRequestOwnerResponse from(StoreRequest storeRequest) {
        return new StoreRequestOwnerResponse(
                storeRequest.getStoreRequestId(),
                storeRequest.getName(),
                storeRequest.getDescription(),
                storeRequest.getStoreCategory().getStoreCategoryId(),
                storeRequest.getLocation().getLocationId(),
                storeRequest.getStatus(),
                storeRequest.getRejectionReason(),
                storeRequest.getCreatedAt(),
                storeRequest.getUpdatedAt(),
                storeRequest.getDeletedAt()
        );
    }
}
