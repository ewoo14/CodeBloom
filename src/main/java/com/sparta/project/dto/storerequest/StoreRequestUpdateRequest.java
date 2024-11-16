package com.sparta.project.dto.storerequest;


public record StoreRequestUpdateRequest(
        String name,
        String description,
        String address,
        String categoryId,
        String locationId
) {
}
