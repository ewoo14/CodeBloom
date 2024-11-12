package com.sparta.project.dto.ai;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Valid
public record AIRequest(
        @NotBlank Long userId,
        @NotBlank String menuId,
        String prompt
) {}
