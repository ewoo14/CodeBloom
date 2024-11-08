package com.sparta.project.dto.user;

import com.sparta.project.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
<<<<<<< HEAD
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserSignupRequest(
        @NotBlank
        @Size(min = 4, max = 10, message = "유저 이름은 최소 4자 이상, 10자 이하여야 합니다.")
        @Pattern(regexp = "^[a-z0-9]+$", message = "유저 이름은 영어 소문자와 숫자로 구성되어야 합니다.")
        String username,

        @NotBlank
        @Size(min = 8, max = 15, message = "비밀번호은 최소 8자 이상, 15자 이하여야 합니다.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                message = "비밀번호는 영어 대소문자, 숫자, 특수문자를 포함해야 합니다.")
        String password,

        @NotBlank
        String nickname,

        @NotNull
        Role role
=======

public record UserSignupRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String nickname,
        @NotBlank Role role
>>>>>>> 2470ae3 ([Feat] 회원가입 API)
) {
}
