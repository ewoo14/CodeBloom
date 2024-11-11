package com.sparta.project.dto.ai;

public record AIRequest(
        Long userId,
        String menuId,
        String prompt
) {}
