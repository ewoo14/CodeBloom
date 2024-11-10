package com.sparta.project.dto.menu;

public record MenuUpdateRequest(
        String name,
        String description,
        Integer price,
        Boolean isClosed
) {}