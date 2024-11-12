package com.sparta.project.dto.ai;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Valid
public record AIRequest(
        @NotNull Long userId,
        @NotBlank String menuId,
        String prompt
) {}
