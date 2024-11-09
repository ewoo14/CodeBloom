package com.sparta.project.domain;

import com.sparta.project.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(
    name="p_user",
    uniqueConstraints = {
        @UniqueConstraint(name="UK_USER", columnNames={"username"})
    }
)
public class User extends BaseEntity { // 유저

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id", nullable=false, updatable=false)
    private Long userId;

    @Column(name="username", length=10, nullable=false) // 유저 고유 네임 (사용자 id)
    private String username;

    @Column(name="password", length=255, nullable=false) // 비밀번호
    private String password;

    @Column(name="nickname", length=10) // 닉네임
    private String nickname;

    @Column(name="role", nullable=false) // 권한
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    private User(String username, String password, String nickname, Role role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public static User create(String username, String password, String nickname, Role role) {
        return User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .role(role)
                .build();
    }
    // 만약 닉네임 초기값을 여기서 설정하고 싶다면, 파라미터에 nickname 지우고, this.nickname = "{defaultName}" 으로 설정하면 됩니다.
    // 닉네임 초기값을 통일한다고 하면, @Builder.Default 활용해도 됩니다.

}
