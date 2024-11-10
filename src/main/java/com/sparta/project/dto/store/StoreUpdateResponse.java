package com.sparta.project.dto.store;

import com.sparta.project.domain.Store;
import lombok.Builder;

@Builder
public record StoreUpdateResponse(
        String storeId,
        String storeName,
        String description,
        String locationId,
        String storeCategoryId
) {

    public static StoreUpdateResponse from(Store store) {
        return StoreUpdateResponse.builder()
                .storeId(store.getStoreId())
                .storeName(store.getName())
                .description(store.getDescription())
                .locationId(store.getLocation().getLocationId())
                .storeCategoryId(store.getStoreCategory().getStoreCategoryId())
                .build();
    }
}
