package com.sparta.project.repository;

import com.sparta.project.domain.Location;
<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface LocationRepository extends JpaRepository<Location, String>, QuerydslPredicateExecutor<Location> {
    boolean existsByName(@NotBlank String name);
=======
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {
>>>>>>> 3e6f9c4 (더미 데이터)
}
