package com.sparta.project.repository;

import com.sparta.project.domain.Store;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
    Page<Store> findAllByOwner(User owner, Pageable pageable);

    Optional<Store> findByOwnerUserId(Long userId);

    @Query("SELECT s.storeId FROM Store s WHERE s.isDeleted = false")
    List<String> findAllStoreIds();

    @Modifying
    @Query("UPDATE Store s SET s.score = :score WHERE s.storeId = :storeId")
    void updateScore(@Param("storeId") String storeId, @Param("score") Double score);
=======
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, String> {
>>>>>>> 3e6f9c4 (더미 데이터)
=======
=======
import com.sparta.project.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> 580d264 ([Feat] 자신의 가게 목록 조회)
import org.springframework.data.jpa.repository.JpaRepository;
=======
>>>>>>> 4ed121a ([Feat] 평점 업데이트 - 1시간마다 스케쥴러 동작)
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
<<<<<<< HEAD
>>>>>>> 9863864 ([Fix] Menu Service 메서드별 테스트 완성)
=======
    Page<Store> findAllByOwner(User owner, Pageable pageable);
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 580d264 ([Feat] 자신의 가게 목록 조회)
=======
    Optional<Store> findByOwnerUserId(Long userId);
>>>>>>> 55dcd96 ([Feat] 자신의 가게 결제 내역 목록 조회 추가)
=======

    Optional<Store> findByOwnerUserId(Long userId);

    @Query("SELECT s.storeId FROM Store s WHERE s.isDeleted = false")
    List<String> findAllStoreIds();

    @Modifying
    @Query("UPDATE Store s SET s.score = :score WHERE s.storeId = :storeId")
    void updateScore(@Param("storeId") String storeId, @Param("score") Double score);
>>>>>>> 4ed121a ([Feat] 평점 업데이트 - 1시간마다 스케쥴러 동작)
}
