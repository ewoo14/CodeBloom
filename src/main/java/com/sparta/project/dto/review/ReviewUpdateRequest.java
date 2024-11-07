package com.sparta.project.dto.review;

public record ReviewUpdateRequest (
        String content,
        Integer score
) {}