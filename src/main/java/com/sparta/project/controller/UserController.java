package com.sparta.project.controller;


import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.user.UserAdminResponse;
import com.sparta.project.dto.user.UserBasicResponse;
import com.sparta.project.dto.user.UserLoginRequest;
import com.sparta.project.dto.user.UserResponse;
import com.sparta.project.dto.user.UserSignupRequest;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.user.UserUpdateRequest;
import com.sparta.project.service.UserService;
import com.sparta.project.util.PermissionValidator;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final PermissionValidator permissionValidator;
    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<Void> signup(@Valid @RequestBody UserSignupRequest request) {
        userService.createUser(request);
        return ApiResponse.success();
    }

    @PostMapping("/login")
    public ApiResponse<Map<String, String>> login(@Valid @RequestBody UserLoginRequest loginUserRequest) {
        String token = userService.login(loginUserRequest);
        return ApiResponse.success(Map.of("token", token));
    }

    // 유저 정보 수정 (ALL)
    @PatchMapping("")
    public ApiResponse<Void> updateUser(Authentication authentication,
                                        @Valid @RequestBody UserUpdateRequest request) {
        userService.updateUser(Long.parseLong(authentication.getName()), request);
        return ApiResponse.success();
    }

    // 회원 탈퇴 (CUSTOMER, OWNER)
    @DeleteMapping("/withdraw")
    public ApiResponse<Void> delete(Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name(), Role.OWNER.name());
        userService.withdraw(Long.parseLong(authentication.getName()));
        return ApiResponse.success();
    }

    // 회원 삭제 (MANAGER, MASTER)
    @DeleteMapping("/{user_id}")
    public ApiResponse<Void> deleteUser(Authentication authentication,
                                        @PathVariable Long user_id) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        userService.deleteUser(Long.parseLong(authentication.getName()), user_id);
        return ApiResponse.success();
    }

    // 자신의 정보 조회 (ALL)
    @GetMapping("/my")
    public ApiResponse<UserResponse> getUser(Authentication authentication) {
        UserResponse result = userService.getUserById(Long.parseLong(authentication.getName()), false);
        return ApiResponse.success(result);
    }

    // 회원 정보 조회 (MANAGER, MASTER)
    @GetMapping("/{user_id}")
    public ApiResponse<UserResponse> getUserById(Authentication authentication,
                                                      @PathVariable Long user_id) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        UserResponse result = userService.getUserById(user_id, true);
        return ApiResponse.success(result);
    }

    // 전체 유저 조회 (MANAGER, MASTER)
    @GetMapping
    public ApiResponse<PageResponse<UserAdminResponse>> getAllUsers(
            Authentication authentication,
            @PageableDefault(size = 5)
            @SortDefault(sort = "createdAt", direction = Direction.DESC)
            Pageable pageable,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "role", required = false) Role role,
            @RequestParam(value = "isDeleted", required = false) Boolean isDeleted) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        Page<UserAdminResponse> result = userService.getAllUsersBy(pageable, username, role, isDeleted);
        return ApiResponse.success(PageResponse.of(result));
    }

}

