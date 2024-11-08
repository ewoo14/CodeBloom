package com.sparta.project.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T> (
        String message,
        T data
) {
<<<<<<< HEAD
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("API 요청에 성공했습니다", data);
    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>("API 요청에 성공했습니다", null);
=======
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, data);
    }

    public static ApiResponse<Void> success(String message) {
        return new ApiResponse<>(message, null);
>>>>>>> cb01438 ([Feat] 공통 응답 객체 추가)
    }
}