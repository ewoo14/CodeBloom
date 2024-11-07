package com.sparta.project.dto.user;

import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.Role;
import java.time.LocalDateTime;

public record UserAdminResponse(
        Long userId,
        String username,
        String nickname,
        Role role,
        Boolean isDeleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        LocalDateTime deletedAt,
        String deletedBy
)  implements UserResponse {
    public static UserAdminResponse from(final User user) {
        return new UserAdminResponse(
                user.getUserId(),
                user.getUsername(),
                user.getNickname(),
                user.getRole(),
                user.getIsDeleted(),
                user.getCreatedAt(),
                user.getCreatedBy(),
                user.getUpdatedAt(),
                user.getUpdatedBy(),
                user.getDeletedAt(),
                user.getDeletedBy()
        );
    }
}
