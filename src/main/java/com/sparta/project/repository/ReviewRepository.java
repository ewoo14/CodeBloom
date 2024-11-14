package com.sparta.project.repository;

import com.sparta.project.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReviewRepository extends JpaRepository<Review, String>, QuerydslPredicateExecutor<Review> {
}
