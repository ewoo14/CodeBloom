package com.sparta.project.dto.store;

import com.sparta.project.domain.Store;
import lombok.Builder;

@Builder
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
        return StoreResponse.builder()
                .storeId(store.getStoreId())
                .storeName(store.getName())
                .ownerId(store.getOwner().getUsername())
                .storeAddress(store.getAddress())
                .description(store.getDescription())
                .categoryId(store.getStoreCategory().getStoreCategoryId())
                .locationId(store.getLocation().getLocationId())
                .score(store.getScore())
                .build();
    }
}
