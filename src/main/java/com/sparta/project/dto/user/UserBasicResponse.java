package com.sparta.project.dto.user;

import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.Role;
import java.time.LocalDateTime;

public record UserBasicResponse(
        Long userId,
        String username,
        String nickname,
        Role role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) implements UserResponse {
    public static UserBasicResponse from(User user) {
        return new UserBasicResponse(
                user.getUserId(),
                user.getUsername(),
                user.getNickname(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
