package com.sparta.project.controller;


import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.user.UserLoginRequest;
import com.sparta.project.dto.user.UserSignupRequest;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.user.UserUpdateRequest;
import com.sparta.project.service.UserService;
import com.sparta.project.util.PermissionValidator;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final PermissionValidator  permissionValidator;
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

}




//
//    // 전체 유저 조회(MANAGER, MASTER)
//    @GetMapping
//    public ApiResponse<PageResponse<UserResponse>> getAllUsers(
//            @RequestParam("username") String username,
//            @RequestParam("role") String role,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy
//    ) {
//        Page<UserResponse> allUsers = userService.getAllUsers(username, role, page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(allUsers));
//    }
//
//    // 유저 단건 조회(MANAGER, MASTER)
//    @GetMapping("/{user_id}")
//    public ApiResponse<UserResponse> getUserById(@PathVariable Long user_id) {
//        UserResponse userInfo = userService.getUserById(user_id);
//        return ApiResponse.success(userInfo);
//    }
//
//    // 회원 탈퇴(ALL)
//    @DeleteMapping("/{user_id}")
//    public ApiResponse<Void> deleteUser(@PathVariable Long user_id) {
//        userService.deleteUser(user_id);
//        return ApiResponse.success();
//    }
//}
