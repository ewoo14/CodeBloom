package com.sparta.project.dto.menu;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Valid
public record MenuCreateRequest(
        @NotBlank String storeId,
        @NotBlank String name,
<<<<<<< HEAD
        String description,
=======
        @NotBlank String description,
>>>>>>> 5c260d6 ([Fix] MenuRequest 객체 분리 & 권한 로직 추가 & UUID 수도 부여)
        @NotNull Integer price,
        @NotNull Boolean isClosed
) {}