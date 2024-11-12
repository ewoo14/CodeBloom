package com.sparta.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_store_category")
public class StoreCategory extends BaseEntity { // 음식점 카테고리

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="store_category_id", length=36, nullable=false, updatable=false)
    private String storeCategoryId;

    @Column(name="name", length=20, nullable=false) // 이름
    private String name;

    @Column(name="description", length=50) // 설명
    private String description;

    @Builder
    private StoreCategory(String storeCategoryId, String name, String description) {
        this.storeCategoryId = storeCategoryId;
        this.name = name;
        this.description = description;
    }

    public static StoreCategory create(String name, String description) {
        return StoreCategory.builder()
                .name(name)
                .description(description)
                .build();
    }

}
