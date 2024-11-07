package com.sparta.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
<<<<<<< HEAD
<<<<<<< HEAD
public abstract class BaseEntity {
=======
public class BaseEntity {
>>>>>>> 6ddac12 ([Fix] createdAt 컬럼 옵션에 "nullable=true" 제거)
=======
public abstract class BaseEntity {
>>>>>>> f6a68d0 ([Fix] BaseEntity 컬럼 수정)
    @Column(name="is_deleted") // 삭제 여부
    @ColumnDefault("false")
    private Boolean isDeleted;

    @CreatedDate
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

<<<<<<< HEAD
    @CreatedBy
    @Column(name="created_by", length=2048)
    private String createdBy;

    @LastModifiedBy
    @Column(name="updated_by", length=2048)
    private String updatedBy;

    @Column(name="deleted_by", length=2048)
    private String deletedBy;

    public void deleteBase(String username) {
=======
//    @CreatedBy
    @Column(name="created_by", length=10, updatable = false)
    private String createdBy;

//    @LastModifiedBy
    @Column(name="updated_by", length=10)
    private String updatedBy;

    @Column(name="deleted_by", length=10)
    private String deletedBy;

    /*
    createdBy와 updatedBy는 스프링 시큐리티 부분이 구현되면 AuditorAware 구현체를 사용하는 방법으로 구현하겠습니다.
    참고: https://javacpro.tistory.com/85
     */

    public void delete(String username) {
>>>>>>> f6a68d0 ([Fix] BaseEntity 컬럼 수정)
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
        this.deletedBy = username;
    }
}
