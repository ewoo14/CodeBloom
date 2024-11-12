package com.sparta.project.dto.location;

<<<<<<< HEAD
<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;

public record LocationRequest (
        @NotBlank String locationName,
=======
import jakarta.validation.constraints.NotNull;
=======
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
>>>>>>> 1fca099 ([Fix] @Valid 어노테이션 추가 및 @NotBlank 변경)

@Valid
public record LocationRequest (
<<<<<<< HEAD
        @NotNull String locationName,
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
=======
        @NotBlank String locationName,
>>>>>>> 1fca099 ([Fix] @Valid 어노테이션 추가 및 @NotBlank 변경)
        String description
) {}