package com.sparta.project.controller;

import com.sparta.project.dto.ai.AIRequest;
import com.sparta.project.dto.ai.AIResponse;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.service.AIService;
import com.sparta.project.util.PermissionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class AIController {

    private final AIService aiService;
    private final PermissionValidator permissionValidator;

    // 생성한 설명 목록 조회
    @GetMapping("/menu-description")
    public ApiResponse<PageResponse<AIResponse>> getMenuDescriptions(
            @RequestParam Long userId,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy) {
        Page<AIResponse> descriptions = aiService.getMenuDescriptions(userId, page, size, sortBy);
        return ApiResponse.success(PageResponse.of(descriptions));
    }

    // 메뉴 설명 생성
    @PostMapping("/menu-description")
    public ApiResponse<AIResponse> createMenuDescription(@RequestBody AIRequest aiRequest, Authentication authentication) {
        permissionValidator.checkPermission(authentication, "OWNER");
        AIResponse madeDescription = aiService.createMenuDescription(aiRequest);
        return ApiResponse.success(madeDescription);
    }
}
