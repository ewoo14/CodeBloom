package com.sparta.project.dto.store;

import com.sparta.project.domain.Store;


public record StoreResponse(
        String storeId,
        String storeName,
        String ownerId,
        String storeAddress,
        String description,
        String categoryId,
        String locationId,
        Double score
) {
    public static StoreResponse from(Store store) {
        return new StoreResponse(
                store.getStoreId(),
                store.getName(),
                String.valueOf(store.getOwner().getUserId()),
                store.getAddress(),
                store.getDescription(),
                store.getStoreCategory().getStoreCategoryId(),
                store.getLocation().getLocationId(),
                store.getScore()
        );
    }
}
