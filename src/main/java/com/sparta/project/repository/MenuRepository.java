package com.sparta.project.repository;

import com.sparta.project.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {

    // 스토어 ID와 스토어 이름으로 메뉴 검색
    Page<Menu> findAllByStoreStoreIdAndStoreName(String storeId, String storeName, Pageable pageable);
}
