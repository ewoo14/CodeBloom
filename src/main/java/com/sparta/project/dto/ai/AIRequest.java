package com.sparta.project.dto.ai;

import jakarta.validation.constraints.NotNull;

public record AIRequest(
        @NotNull Long userId,
        @NotNull String menuId,
        String prompt
) {}
