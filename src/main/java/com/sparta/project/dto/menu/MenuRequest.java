package com.sparta.project.dto.menu;

public record MenuRequest(
        String storeId,
        String name,
        String description,
        Integer price,
        Boolean isClosed
) {}