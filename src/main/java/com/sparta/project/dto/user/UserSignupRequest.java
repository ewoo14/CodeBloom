package com.sparta.project.dto.user;

import com.sparta.project.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserSignupRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String nickname,
        @NotNull Role role
) {
}
