package com.sparta.project.service;

import com.sparta.project.domain.StoreCategory;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.StoreCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreCategoryService {
    private final StoreCategoryRepository storeCategoryRepository;

    public StoreCategory getStoreCategoryOrException(String categoryId) {
        return storeCategoryRepository.findById(categoryId).orElseThrow(()->
                new CodeBloomException(ErrorCode.CATEGORY_NOT_FOUND));
    }

}
