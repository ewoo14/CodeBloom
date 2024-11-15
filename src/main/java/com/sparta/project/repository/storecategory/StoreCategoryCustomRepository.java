package com.sparta.project.repository.storecategory;

import com.sparta.project.domain.StoreCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreCategoryCustomRepository {
    Page<StoreCategory> findAllWith(Pageable pageable, String name, Boolean isDeleted);
}
