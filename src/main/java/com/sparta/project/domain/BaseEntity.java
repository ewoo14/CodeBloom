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
public abstract class BaseEntity {
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

//    @CreatedBy
    @Column(name="created_by", length=10)
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

    public void deleteBase(String username) {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
        this.deletedBy = username;
    }
}
