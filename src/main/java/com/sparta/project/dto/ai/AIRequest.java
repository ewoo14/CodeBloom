package com.sparta.project.dto.ai;

<<<<<<< HEAD
<<<<<<< HEAD
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
=======
import jakarta.validation.Valid;
=======
>>>>>>> 05ce6cb ([Fix] Valid 어노테이션 컨트롤러 이동, 권한 주석 수정)
import jakarta.validation.constraints.NotBlank;
<<<<<<< HEAD
>>>>>>> 1fca099 ([Fix] @Valid 어노테이션 추가 및 @NotBlank 변경)

@Valid
public record AIRequest(
<<<<<<< HEAD
        @NotNull Long userId,
        @NotNull String menuId,
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
=======
        @NotBlank Long userId,
=======
import jakarta.validation.constraints.NotNull;

public record AIRequest(
        @NotNull Long userId,
>>>>>>> 6d81f05 ([Fix] Long 타입 user_id §@NotBlank§ -> §@NotNull§ 변경)
        @NotBlank String menuId,
>>>>>>> 1fca099 ([Fix] @Valid 어노테이션 추가 및 @NotBlank 변경)
        String prompt
) {}
