package com.sparta.project.service;

import com.sparta.project.domain.Ai;
import com.sparta.project.domain.User;
import com.sparta.project.dto.ai.AIRequest;
import com.sparta.project.dto.ai.AIResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.AIRepository;
import com.sparta.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class AIService {

    private final UserRepository userRepository;
    private final AIRepository aiRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${api.key}")
    private String apiKey;

    public AIService(UserRepository userRepository, AIRepository aiRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.aiRepository = aiRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    // 새로운 메뉴 설명 생성
    @Transactional
    public AIResponse createMenuDescription(AIRequest aiRequest) {
        User user = userRepository.findById(aiRequest.userId())
                .orElseThrow(() -> new CodeBloomException(ErrorCode.USER_NOT_FOUND));
        String response = sendPostRequest(aiRequest.prompt(), 150);
        Ai ai = Ai.create(user, aiRequest.prompt(), response);
        ai = aiRepository.save(ai);
        return AIResponse.from(ai);
    }

    // 생성한 설명 목록 조회
    @Transactional(readOnly = true)
    public Page<AIResponse> getMenuDescriptions(String menuId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));
        Page<Ai> results = aiRepository.findAll(pageable);

        if (results.isEmpty()) {
            throw new CodeBloomException(ErrorCode.AIDESCRIPTION_NOT_FOUND);
        }

        return results.map(AIResponse::from);
    }

    // GPT API에 응답 요청
    private String sendPostRequest(String prompt, int maxTokens) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = Map.of(
                "model", "gpt-4o",
                "prompt", prompt,
                "max_tokens", maxTokens
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://api.openai.com/v1/chat/completions", entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return extractContentFromResponse(response.getBody());
        } else {
            throw new CodeBloomException(ErrorCode.API_CALL_FAILED);
        }
    }

    // JSON 응답에서 content 추출
    private String extractContentFromResponse(String response) {
        try {
            JsonNode root = objectMapper.readTree(response);
            return root.path("choices").get(0).path("text").asText();
        } catch (IOException e) {
            throw new CodeBloomException(ErrorCode.RESPONSE_PARSING_ERROR);
        }
    }
}
