package com.sparta.project.controller;

<<<<<<< HEAD
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            }) Pageable pageable,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name());
        Page<AIResponse> descriptions = aiService.getMenuDescriptions(userId, pageable);
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
=======
import com.sparta.project.dto.ai.AIChatRequest;
import com.sparta.project.dto.ai.AIChatResponse;
import com.sparta.project.dto.ai.AIDescRequest;
import com.sparta.project.dto.ai.AIDescResponse;
import com.sparta.project.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class AIController {

    private AIService aiService;

    // 챗봇 질문에 대한 답변 요청
    @PostMapping("/chat")
    public AIChatResponse postChat(@RequestBody AIChatRequest aiChatRequest) {
        return aiService.processChat(aiChatRequest);
    }

    // 음식 설명 생성
    @PostMapping("/desc")
    public AIDescResponse postDescription(@RequestBody AIDescRequest aiDescRequest) {
        return aiService.generateDescription(aiDescRequest);
>>>>>>> c220516 ([Feat] : 컨트롤러 초안 작성)
    }
}
