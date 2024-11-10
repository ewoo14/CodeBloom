package com.sparta.project.dto.ai;

<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AIRequest(
        @NotNull Long userId,
        @NotBlank String menuId,
=======
public record AIRequest(
        Long userId,
        String menuId,
>>>>>>> fea02e7 ([Feat] AI Dto 및 service 구현)
        String prompt
) {}
