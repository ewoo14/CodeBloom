package com.sparta.project.repository;

import com.sparta.project.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, String>, QuerydslPredicateExecutor<Review> {
    @Query("SELECT ROUND(AVG(CAST(r.score AS double)), 1) FROM Review r WHERE r.store.storeId = :storeId AND r.isDeleted = false")
    Double calculateAverageScoreByStoreId(@Param("storeId") String storeId);
}
