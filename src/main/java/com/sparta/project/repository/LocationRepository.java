package com.sparta.project.repository;

import com.sparta.project.domain.Location;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface LocationRepository extends JpaRepository<Location, String>, QuerydslPredicateExecutor<Location> {
    boolean existsByName(@NotBlank String name);
<<<<<<< HEAD
=======
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {
>>>>>>> 3e6f9c4 (더미 데이터)
=======
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
}
