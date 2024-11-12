package com.sparta.project.dto.store;

import com.sparta.project.domain.Location;
import com.sparta.project.domain.StoreCategory;
import com.sparta.project.domain.StoreRequest;
import com.sparta.project.domain.User;

public record StoreCreateData(
        String name,
        String description,
        String address,
        User owner,
        StoreCategory storeCategory,
        Location location
) {
    public static StoreCreateData from(StoreRequest request) {
        return new StoreCreateData(
                request.getName(),
                request.getDescription(),
                request.getAddress(),
                request.getOwner(),
                request.getStoreCategory(),
                request.getLocation()
        );
    }
}
