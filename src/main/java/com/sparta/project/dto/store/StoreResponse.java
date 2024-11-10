package com.sparta.project.dto.store;

import com.sparta.project.domain.Store;
<<<<<<< HEAD


=======
import lombok.Builder;

@Builder
>>>>>>> b8d01e9 ([Feat] 음식점 상세 조회 기능 Service 및 ServiceTest)
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
<<<<<<< HEAD
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
=======
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
>>>>>>> b8d01e9 ([Feat] 음식점 상세 조회 기능 Service 및 ServiceTest)
    }
}
