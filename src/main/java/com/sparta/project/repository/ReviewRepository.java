package com.sparta.project.repository;

import com.sparta.project.domain.Review;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, String>, QuerydslPredicateExecutor<Review> {
    @Query("SELECT ROUND(AVG(CAST(r.score AS double)), 1) FROM Review r WHERE r.store.storeId = :storeId AND r.isDeleted = false")
    Double calculateAverageScoreByStoreId(@Param("storeId") String storeId);
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReviewRepository extends JpaRepository<Review, String>, QuerydslPredicateExecutor<Review> {
>>>>>>> 0ebca46 ([Feat] 리뷰 생성 및 조회 구현)
}
