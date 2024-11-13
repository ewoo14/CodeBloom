package com.sparta.project.dto.store;

public record StoreUpdateRequest(
        String storeName,
        String description,
        String locationId,
        String categoryId
) {
}

