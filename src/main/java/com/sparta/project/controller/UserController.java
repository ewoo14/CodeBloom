//package com.sparta.project.controller;
//
//import com.sparta.project.dto.user.CreateUserRequest;
//import com.sparta.project.dto.user.LoginUserRequest;
//import com.sparta.project.dto.user.UpdateUserRequest;
//import com.sparta.project.dto.user.UserResponse;
//import com.sparta.project.dto.common.ApiResponse;
//import com.sparta.project.dto.common.PageResponse;
//import com.sparta.project.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/users")
//public class UserController {
//
//    private final UserService userService;
//
//    // 회원가입(ALL)
//    @PostMapping("/signup")
//    public ApiResponse<UserResponse> signUp(@RequestBody CreateUserRequest createUserRequest) {
//        UserResponse userResponse = userService.createUser(createUserRequest);
//        return ApiResponse.success(userResponse);
//    }
//
//    // 로그인(ALL)
//    @PostMapping("/login")
//    public ApiResponse<String> login(@RequestBody LoginUserRequest loginUserRequest) {
//        String token = userService.loginUser(loginUserRequest);
//        return ApiResponse.success(token);
//    }
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
//    // 회원정보 수정(ALL)
//    @PatchMapping("/{user_id}")
//    public ApiResponse<UserResponse> updateUser(
//            @PathVariable Long user_id,
//            @RequestBody UpdateUserRequest updateUserRequest) {
//        UserResponse updatedUser = userService.updateUser(user_id, updateUserRequest);
//        return ApiResponse.success(updatedUser);
//    }
//
//    // 회원 탈퇴(ALL)
//    @DeleteMapping("/{user_id}")
//    public ApiResponse<Void> deleteUser(@PathVariable Long user_id) {
//        userService.deleteUser(user_id);
//        return ApiResponse.success();
//    }
//}
