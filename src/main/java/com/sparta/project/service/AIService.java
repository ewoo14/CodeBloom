package com.sparta.project.service;

import com.sparta.project.domain.Ai;
import com.sparta.project.domain.QAi;
import com.sparta.project.domain.User;
import com.sparta.project.dto.ai.AIRequest;
import com.sparta.project.dto.ai.AIResponse;
import com.sparta.project.dto.menu.MenuResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.AIRepository;
import com.sparta.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AIService {

    private final UserRepository userRepository;
    private final AIRepository aiRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(AIService.class);

    @Value("${api.key}")
    private String apiKey;

    public AIService(UserRepository userRepository, AIRepository aiRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.aiRepository = aiRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    // 권한 확인
    private void checkPermission(Authentication authentication, String... roles) {
        log.info("권한 검사: {}", (Object[]) roles);
        boolean hasPermission = Arrays.stream(roles)
                .anyMatch(role -> authentication.getAuthorities()
                        .contains(new SimpleGrantedAuthority(role)));
        if (!hasPermission) {
            log.info("액세스가 거부되었습니다. 현재 유저의 권한: {}", authentication.getAuthorities());
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }

    // 생성한 설명 목록 조회
    @Transactional(readOnly = true)
    public Page<AIResponse> getMenuDescriptions(Long userId, int page, int size, String sortBy) {
        QAi qAi = QAi.ai;
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));

        BooleanExpression predicate = qAi.user.userId.eq(userId);
        Page<Ai> results = aiRepository.findAll(predicate, pageable);
        if (results.isEmpty()) {
            throw new CodeBloomException(ErrorCode.AIDESCRIPTION_NOT_FOUND);
        }

        return results.map(AIResponse::from);
    }

    // 새로운 메뉴 설명 생성
    @Transactional
    public AIResponse createMenuDescription(AIRequest aiRequest, Authentication authentication) {
        checkPermission(authentication, "OWNER");
        User user = userRepository.findById(aiRequest.userId())
                .orElseThrow(() -> new CodeBloomException(ErrorCode.USER_NOT_FOUND));
        String response = sendPostRequest(aiRequest.prompt(), 150);
        Ai ai = Ai.create(user, aiRequest.prompt(), response);
        ai = aiRepository.save(ai);
        return AIResponse.from(ai);
    }

    // GPT API에 응답 요청
    private String sendPostRequest(String prompt, int maxTokens) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String limitPromptSize = prompt + " 답변을 최대한 간결하게 50자 이하로";

        List<Map<String, Object>> messages = List.of(
                Map.of("role", "user", "content", limitPromptSize)
        );

        Map<String, Object> body = Map.of(
                "model", "gpt-4o",
                "messages", messages,
                "max_tokens", maxTokens
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://api.openai.com/v1/chat/completions", entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("API 호출 후 받아온 응답: " + response.getBody());
            return extractContentFromResponse(response.getBody());
        } else {
            throw new CodeBloomException(ErrorCode.API_CALL_FAILED);
        }
    }

    // JSON 응답에서 content 추출
    private String extractContentFromResponse(String response) {
        try {
            JsonNode root = objectMapper.readTree(response);
            String content = root.path("choices").get(0).path("message").path("content").asText();
            log.info("content 응답 파싱: " + content);
            return content;
        } catch (IOException e) {
            throw new CodeBloomException(ErrorCode.RESPONSE_PARSING_ERROR);
        }
    }
}
