package com.sparta.project.controller;

import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.ai.AIRequest;
import com.sparta.project.dto.ai.AIResponse;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.permission.PermissionValidator;
import com.sparta.project.service.AIService;
import jakarta.validation.Valid;
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

    // 생성한 설명 목록 조회(OWNER)
    @GetMapping("/menu-description")
    public ApiResponse<PageResponse<AIResponse>> getMenuDescriptions(
            @RequestParam Long userId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
        Page<AIResponse> descriptions = aiService.getMenuDescriptions(userId, page, size);
        return ApiResponse.success(PageResponse.of(descriptions));
    }

    // 메뉴 설명 생성(OWNER)
    @PostMapping("/menu-description")
    public ApiResponse<AIResponse> createMenuDescription(
            @Valid @RequestBody AIRequest aiRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
        AIResponse madeDescription = aiService.createMenuDescription(aiRequest);
        return ApiResponse.success(madeDescription);
    }
}
