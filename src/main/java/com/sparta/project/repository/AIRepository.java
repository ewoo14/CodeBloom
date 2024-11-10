package com.sparta.project.repository;

import com.sparta.project.domain.Ai;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AIRepository extends JpaRepository<Ai, String>, QuerydslPredicateExecutor<Ai> {
=======
import org.springframework.stereotype.Repository;

@Repository
public interface AIRepository extends JpaRepository<Ai, String> {
>>>>>>> fea02e7 ([Feat] AI Dto 및 service 구현)
}