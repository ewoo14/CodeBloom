package com.sparta.project.controller;

import com.sparta.project.dto.category.CategoryRequest;
import com.sparta.project.dto.category.CategoryResponse;
import com.sparta.project.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    // 음식점 카테고리 목록 조회
    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // 음식점 카테고리 상세 조회
    @GetMapping("/{category_id}")
    public CategoryResponse getCategoryById(@PathVariable String category_id) {
        return categoryService.getCategoryById(category_id);
    }

    // 음식점 카테고리 생성
    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    // 음식점 카테고리 수정
    @PatchMapping("/{category_id}")
    public CategoryResponse updateCategory(@PathVariable String category_id, @RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategory(category_id, categoryRequest);
    }

    // 음식점 카테고리 삭제
    @DeleteMapping("/{category_id}")
    public void deleteCategory(@PathVariable String category_id) {
        categoryService.deleteCategory(category_id);
    }
}
