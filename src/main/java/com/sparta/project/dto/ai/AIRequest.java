package com.sparta.project.dto.ai;

<<<<<<< HEAD
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
=======
import jakarta.validation.constraints.NotNull;

public record AIRequest(
        @NotNull Long userId,
        @NotNull String menuId,
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
        String prompt
) {}
