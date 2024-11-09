package com.sparta.project.dto.menu;

public record MenuResponse(
        String menuId,
        String storeId,
        String name,
        String description,
        Integer price,
        Boolean isClosed
) {}