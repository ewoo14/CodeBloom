package com.sparta.project.service;

import com.sparta.project.domain.StoreCategory;
import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.storecategory.StoreCategoryAdminResponse;
import com.sparta.project.dto.storecategory.StoreCategoryCreateRequest;
import com.sparta.project.dto.storecategory.StoreCategoryResponse;
import com.sparta.project.dto.storecategory.StoreCategoryUpdateRequest;
import com.sparta.project.dto.storecategory.StoreCategoryUserResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.storecategory.StoreCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreCategoryService {

    private final UserService userService;
    private final StoreCategoryRepository storeCategoryRepository;

    public StoreCategory getStoreCategoryOrException(String categoryId) {
        return storeCategoryRepository.findById(categoryId).orElseThrow(()->
                new CodeBloomException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    @Transactional
    public void createStoreCategory(final StoreCategoryCreateRequest request) {
        checkNameDuplication(request.name());
        storeCategoryRepository.save(StoreCategory.create(request.name(), request.description()));
    }

    @Transactional
    public void updateStoreCategory(String categoryId, final StoreCategoryUpdateRequest request) {
        StoreCategory storeCategory = getStoreCategoryOrException(categoryId);
        if(request.name()!=null) checkNameDuplication(request.name());
        storeCategory.update(request.name(), request.description());
    }

    @Transactional
    public void deleteStoreCategory(String userId, String categoryId) {
        StoreCategory storeCategory = getStoreCategoryOrException(categoryId);
        storeCategory.deleteBase(userId);
    }

    @Transactional(readOnly = true)
    public Page<StoreCategoryResponse> getAllCategoriesBy(long userId,
                                                          Pageable pageable,
                                                          String categoryName,
                                                          Boolean isDeleted) {
        User user = userService.getUserOrException(userId);
        if(!isAdmin(user.getRole()) && isDeleted!=null) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
        return (isAdmin(user.getRole()))
                ? storeCategoryRepository.findAllWith(pageable, categoryName, isDeleted)
                        .map(StoreCategoryAdminResponse::from)
                : storeCategoryRepository.findAllWith(pageable, categoryName, false)
                        .map(StoreCategoryUserResponse::from);
    }

    @Transactional(readOnly = true)
    public StoreCategoryResponse getCategoryById(long userId, String categoryId) {
        StoreCategory storeCategory = getStoreCategoryOrException(categoryId);
        User user = userService.getUserOrException(userId);
        if(!isAdmin(user.getRole()) && storeCategory.getIsDeleted()!=null) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
        return (isAdmin(user.getRole()))
                ? StoreCategoryAdminResponse.from(storeCategory)
                : StoreCategoryUserResponse.from(storeCategory);
    }

    private boolean isAdmin(Role role) {
        return role == Role.MANAGER || role == Role.MASTER;
    }

    private void checkNameDuplication(String newName) {
        if(storeCategoryRepository.existsByName(newName)) {
            throw new CodeBloomException(ErrorCode.CATEGORY_ALREADY_EXIST);
        }
    }

}
