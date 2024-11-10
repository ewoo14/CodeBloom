package com.sparta.project.dto.menu;

import com.sparta.project.domain.Menu;

public record MenuResponse(
        String menuId,
        String storeId,
        String name,
        String description,
        Integer price,
        Boolean isClosed
) {
    public static MenuResponse from(Menu menu) {
        return new MenuResponse(
                menu.getMenuId(),
                menu.getStore().getStoreId(),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice(),
                menu.getIsClosed()
        );
    }
}