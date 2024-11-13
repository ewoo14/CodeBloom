<<<<<<< HEAD
<<<<<<< HEAD
package com.sparta.project.controller;

<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.domain.enums.Role;
=======
package com.sparta.project.controller;

>>>>>>> fea02e7 ([Feat] AI Dto 및 service 구현)
import com.sparta.project.dto.ai.AIRequest;
import com.sparta.project.dto.ai.AIResponse;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
<<<<<<< HEAD
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

=======
import com.sparta.project.service.AIService;
import com.sparta.project.util.PermissionValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

>>>>>>> fea02e7 ([Feat] AI Dto 및 service 구현)
@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class AIController {

    private final AIService aiService;
<<<<<<< HEAD
    private final PermissionValidator permissionValidator;

    // 생성한 설명 목록 조회(OWNER)
<<<<<<< HEAD
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
=======
import com.sparta.project.dto.AIChatRequest;
import com.sparta.project.dto.AIChatResponse;
import com.sparta.project.dto.AIDescRequest;
import com.sparta.project.dto.AIDescResponse;
>>>>>>> d0a8147 ([Fix] controller 내 dto 경로 수정)
import com.sparta.project.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class AIController {

<<<<<<< HEAD
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
=======
//package com.sparta.project.controller;
//
//import com.sparta.project.dto.ai.AIRequest;
//import com.sparta.project.dto.ai.AIResponse;
//import com.sparta.project.dto.common.ApiResponse;
//import com.sparta.project.dto.common.PageResponse;
//import com.sparta.project.service.AIService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/ai")
//public class AIController {
//
//    private final AIService aiService;
//
//    // 메뉴 설명 생성
//    @PostMapping("/menu-description")
//    public ApiResponse<AIResponse> createMenuDescription(@RequestBody AIRequest aiRequest) {
//        AIResponse madeDescription = aiService.createMenuDescription(aiRequest);
//        return ApiResponse.success(madeDescription);
//    }
//
//    // 생성한 설명 목록 조회
//    @GetMapping("/menu-description")
//    public ApiResponse<AIResponse> getMenuDescription(
//            @RequestParam String menuId,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<AIResponse> descriptions = aiService.getMenuDescriptions(menuId, page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(descriptions));
//    }
//}
>>>>>>> 5f194e3 ([Fix] AI명세서대로 컨트롤러 초안 수정)
=======
=======
    private final AIService aiService;
    private final PermissionValidator permissionValidator;
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)

    // 생성한 설명 목록 조회
=======
>>>>>>> f88e08e ([Fix] AI 권한 조정)
    @GetMapping("/menu-description")
    public ApiResponse<PageResponse<AIResponse>> getMenuDescriptions(
            @RequestParam Long userId,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "OWNER");
        Page<AIResponse> descriptions = aiService.getMenuDescriptions(userId, page, size, sortBy);
        return ApiResponse.success(PageResponse.of(descriptions));
    }

    // 메뉴 설명 생성(OWNER)
    @PostMapping("/menu-description")
    public ApiResponse<AIResponse> createMenuDescription(
            @Valid @RequestBody AIRequest aiRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "OWNER");
        AIResponse madeDescription = aiService.createMenuDescription(aiRequest);
        return ApiResponse.success(madeDescription);
    }
}
>>>>>>> fea02e7 ([Feat] AI Dto 및 service 구현)
