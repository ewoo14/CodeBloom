package com.sparta.project.repository.storecategory;

import com.sparta.project.domain.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface StoreCategoryRepository extends JpaRepository<StoreCategory, String>, StoreCategoryCustomRepository {
    boolean existsByName(String name);
}
