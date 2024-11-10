package com.sparta.project.repository;

import com.sparta.project.domain.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory, String> {
}
