package com.sparta.project.dto.ai;

import com.sparta.project.domain.Ai;

public record AIResponse(
        String aiId,
        Long userId,
        String question,
        String answer
) {
    public static AIResponse from(Ai ai) {
        return new AIResponse(
                ai.getAiId(),
                ai.getUser().getUserId(),
                ai.getQuestion(),
                ai.getAnswer()
        );
    }
}
