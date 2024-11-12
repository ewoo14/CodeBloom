package com.sparta.project.repository;

import com.sparta.project.domain.Menu;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String>, QuerydslPredicateExecutor<Menu> {
    boolean existsByName(@NotBlank String name);
<<<<<<< HEAD
}
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
>>>>>>> dc05aea ([Fix] 전체 메뉴 조회에 queryDSL 적용)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public interface MenuRepository extends JpaRepository<Menu, String> {

    // 스토어 ID와 스토어 이름으로 메뉴 검색
    Page<Menu> findAllByStoreStoreIdAndStoreName(String storeId, String storeName, Pageable pageable);
}
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
public interface MenuRepository extends JpaRepository<Menu, String>, QuerydslPredicateExecutor<Menu> {
}
>>>>>>> dc05aea ([Fix] 전체 메뉴 조회에 queryDSL 적용)
=======
}
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
