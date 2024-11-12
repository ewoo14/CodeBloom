package com.sparta.project.dto.location;

<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;

public record LocationRequest (
        @NotBlank String locationName,
=======
import jakarta.validation.constraints.NotNull;

public record LocationRequest (
        @NotNull String locationName,
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
        String description
) {}