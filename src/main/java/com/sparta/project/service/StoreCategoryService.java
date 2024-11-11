package com.sparta.project.service;

import com.sparta.project.domain.StoreCategory;
import com.sparta.project.dto.storecategory.StoreCategoryCreateRequest;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.StoreCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreCategoryService {

    private final StoreCategoryRepository storeCategoryRepository;

    public StoreCategory getStoreCategoryOrException(String categoryId) {
        return storeCategoryRepository.findById(categoryId).orElseThrow(()->
                new CodeBloomException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    @Transactional
    public void createStoreCategory(final StoreCategoryCreateRequest request) {
        checkNameDuplication(request.name());
        storeCategoryRepository.save(StoreCategory.builder()
                .name(request.name())
                .description(request.description())
                .build()
        );
    }

    private void checkNameDuplication(String newName) {
        if(storeCategoryRepository.existsByName(newName)) {
            throw new CodeBloomException(ErrorCode.CATEGORY_ALREADY_EXIST);
        }
    }

}
