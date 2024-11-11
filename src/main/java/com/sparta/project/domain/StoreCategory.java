package com.sparta.project.domain;

<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
<<<<<<< HEAD
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
=======
>>>>>>> 94cacd4 ([Style] category -> store_category로 변경)
import jakarta.persistence.Id;
import jakarta.persistence.Table;
=======
import jakarta.persistence.*;
>>>>>>> 377f60d ([Build] ErrorCode 정돈, Location, StoreCategory, Store 의 id 자동 생성 되도록)
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name = "p_store_category")
public class StoreCategory extends BaseEntity { // 음식점 카테고리

    @Id
<<<<<<< HEAD
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.UUID)
=======
>>>>>>> 94cacd4 ([Style] category -> store_category로 변경)
    @Column(name="store_category_id", length=36, nullable=false, updatable=false)
=======
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "store_category_id", length = 36, nullable = false, updatable = false)
>>>>>>> 377f60d ([Build] ErrorCode 정돈, Location, StoreCategory, Store 의 id 자동 생성 되도록)
    private String storeCategoryId;

    @Column(name = "name", length = 20, nullable = false) // 이름
    private String name;

    @Column(name = "description", length = 50) // 설명
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
<<<<<<< HEAD
    public StoreCategory(String storeCategoryId, String name, String description) {
        this.storeCategoryId = storeCategoryId;
>>>>>>> 94cacd4 ([Style] category -> store_category로 변경)
=======
    public StoreCategory(String name, String description) {
>>>>>>> 377f60d ([Build] ErrorCode 정돈, Location, StoreCategory, Store 의 id 자동 생성 되도록)
        this.name = name;
        this.description = description;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public static StoreCategory create(String name, String description) {
        return StoreCategory.builder()
=======
    public static StoreCategory create(String storeCategoryId, String name, String description) {
        return StoreCategory.builder()
<<<<<<< HEAD
                .storeCategoryId(storeCategoryId)
>>>>>>> 4d314db ([Build] Store를 생성하기 위한 Location, StoreCategory 정적팩토리 메서드 + ErrorCode 추가)
=======
>>>>>>> 377f60d ([Build] ErrorCode 정돈, Location, StoreCategory, Store 의 id 자동 생성 되도록)
                .name(name)
                .description(description)
                .build();
    }
<<<<<<< HEAD

=======
>>>>>>> 94cacd4 ([Style] category -> store_category로 변경)
=======
>>>>>>> 4d314db ([Build] Store를 생성하기 위한 Location, StoreCategory 정적팩토리 메서드 + ErrorCode 추가)
}
