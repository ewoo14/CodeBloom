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
