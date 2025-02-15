package com.sparta.project.dto.common;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponse<T> (
        int totalPages,
        int pageNumber,
        List<T> content
) {
    public static <T> PageResponse<T> of(Page<T> page){
        return new PageResponse<>(
                page.getTotalPages(),
                page.getNumber() + 1,
                page.getContent()
        );
    }
}