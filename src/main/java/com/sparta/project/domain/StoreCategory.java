package com.sparta.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
<<<<<<< HEAD
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
=======
>>>>>>> 94cacd4 ([Style] category -> store_category로 변경)
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
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.UUID)
=======
>>>>>>> 94cacd4 ([Style] category -> store_category로 변경)
    @Column(name="store_category_id", length=36, nullable=false, updatable=false)
    private String storeCategoryId;

    @Column(name="name", length=20, nullable=false) // 이름
    private String name;

    @Column(name="description", length=50) // 설명
    private String description;

<<<<<<< HEAD
    public void update(String name, String description) {
        if(name!=null) this.name = name;
        if(description!=null) this.description = description;
    }

    @Builder
    private StoreCategory(String name, String description) {
=======
    @Builder
    public StoreCategory(String storeCategoryId, String name, String description) {
        this.storeCategoryId = storeCategoryId;
>>>>>>> 94cacd4 ([Style] category -> store_category로 변경)
        this.name = name;
        this.description = description;
    }

<<<<<<< HEAD
    public static StoreCategory create(String name, String description) {
        return StoreCategory.builder()
                .name(name)
                .description(description)
                .build();
    }

=======
>>>>>>> 94cacd4 ([Style] category -> store_category로 변경)
}
