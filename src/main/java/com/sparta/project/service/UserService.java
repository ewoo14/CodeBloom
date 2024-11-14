package com.sparta.project.service;


<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.config.jwt.JwtUtil;
import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.user.UserAdminResponse;
import com.sparta.project.dto.user.UserBasicResponse;
import com.sparta.project.dto.user.UserLoginRequest;
import com.sparta.project.dto.user.UserResponse;
import com.sparta.project.dto.user.UserSignupRequest;
import com.sparta.project.dto.user.UserUpdateRequest;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
import com.sparta.project.config.jwt.TokenProvider;
import com.sparta.project.config.jwt.UserAuthentication;
=======
import com.sparta.project.config.jwt.JwtUtil;
>>>>>>> f0cc95c ([Fix] 토큰 정보에 유저 Role 정보 포함되도록 변경)
import com.sparta.project.domain.User;
import com.sparta.project.dto.user.UserLoginRequest;
import com.sparta.project.dto.user.UserSignupRequest;
import com.sparta.project.dto.user.UserUpdateRequest;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
>>>>>>> 2470ae3 ([Feat] 회원가입 API)
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

<<<<<<< HEAD
<<<<<<< HEAD
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserOrException(long userId) {
        return userRepository.findById(userId).orElseThrow(()->
                new CodeBloomException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional
    public void createUser(final UserSignupRequest request) {
        if(userRepository.existsByUsername(request.username())) {
            throw new CodeBloomException(ErrorCode.DUPLICATE_USERNAME);
        }
        userRepository.save(User.create(
                request.username(), passwordEncoder.encode(request.password()),
                request.nickname(), request.role())
        );
    }

    public String login(final UserLoginRequest request) {
        User user = userRepository.findByUsername(request.username()).orElseThrow(()->
                new CodeBloomException(ErrorCode.USER_NOT_FOUND));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new CodeBloomException(ErrorCode.INVALID_PASSWORD);
        }
        return jwtUtil.generateToken(String.valueOf(user.getUserId()), user.getRole());
    }

    @Transactional
    public void updateUser(long userId, final UserUpdateRequest request) {
        User user = getUserOrException(userId);
        user.update(
                request.username(),
                (request.password()!=null)?passwordEncoder.encode(request.password()):null,
                request.nickname()
        );
    }

<<<<<<< HEAD
<<<<<<< HEAD
    @Transactional(readOnly = true)
    public UserResponse getUserById(long userId, boolean isAdmin) {
        User user = getUserOrException(userId);
        return (isAdmin)
                ? UserAdminResponse.from(user)
                : UserBasicResponse.from(user);
    }

    @Transactional(readOnly = true)
    public Page<UserAdminResponse> getAllUsersBy(
            Pageable pageable,
            String username,
            Role role,
            Boolean isDeleted ) {
        return userRepository.findUserWith(pageable, username, role, isDeleted)
                .map(UserAdminResponse::from);
    }

=======
>>>>>>> 9ab9550 ([Feat] 회원 탈퇴 API)
    @Transactional
    public void withdraw(long userId) {
        User user = getUserOrException(userId);
        user.deleteBase(String.valueOf(userId));
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 0d2cb8a ([Feat] 회원 삭제 API)

    @Transactional
    public void deleteUser(long adminId, long userId) {
        User user = getUserOrException(userId);
        if(user.getIsDeleted()) {
            throw new CodeBloomException(ErrorCode.ALREADY_PROCESSED);
        }
        user.deleteBase(String.valueOf(adminId));
    }

<<<<<<< HEAD
=======
    private final TokenProvider tokenProvider;
=======
    private final JwtUtil jwtUtil;
>>>>>>> f0cc95c ([Fix] 토큰 정보에 유저 Role 정보 포함되도록 변경)
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(final UserSignupRequest request) {
        // TODO : 유효성 검사 필요
        userRepository.save(User.create(
                request.username(), passwordEncoder.encode(request.password()),
                request.nickname(), request.role())
        );
    }

<<<<<<< HEAD
>>>>>>> 2470ae3 ([Feat] 회원가입 API)
=======
    public String login(final UserLoginRequest request) {
        User user = userRepository.findByUsername(request.username()).orElseThrow(()->
                new CodeBloomException(ErrorCode.USER_NOT_FOUND));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new CodeBloomException(ErrorCode.INVALID_PASSWORD);
        }
        return jwtUtil.generateToken(String.valueOf(user.getUserId()), user.getRole());
    }

>>>>>>> 4bb355b ([Feat] 로그인 API)
=======
>>>>>>> a74bec8 ([Feat] 유저 정보 수정 API)
=======
>>>>>>> 9ab9550 ([Feat] 회원 탈퇴 API)
=======
>>>>>>> 0d2cb8a ([Feat] 회원 삭제 API)
}
