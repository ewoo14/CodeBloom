package com.sparta.project.repository;

import com.sparta.project.domain.Review;
<<<<<<< HEAD
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
=======
>>>>>>> 4ed121a ([Feat] 평점 업데이트 - 1시간마다 스케쥴러 동작)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, String>, QuerydslPredicateExecutor<Review> {
<<<<<<< HEAD
>>>>>>> 0ebca46 ([Feat] 리뷰 생성 및 조회 구현)
=======
    @Query("SELECT AVG(CAST(r.score AS double)) FROM Review r WHERE r.store.storeId = :storeId AND r.isDeleted = false")
    Double calculateAverageScoreByStoreId(@Param("storeId") String storeId);
>>>>>>> 4ed121a ([Feat] 평점 업데이트 - 1시간마다 스케쥴러 동작)
}
