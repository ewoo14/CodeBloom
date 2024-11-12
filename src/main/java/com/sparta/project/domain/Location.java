package com.sparta.project.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name = "p_location")
public class Location extends BaseEntity { // 지역

    @Id
<<<<<<< HEAD
<<<<<<< HEAD
    @GeneratedValue(strategy=GenerationType.UUID)
=======
    @GeneratedValue(strategy = GenerationType.AUTO)
>>>>>>> a76aa9b ([Fix] UUID DB 자동 할당 방식으로 변경)
    @Column(name="location_id", length=36, nullable=false, updatable=false)
=======
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "location_id", length = 36, nullable = false, updatable = false)
>>>>>>> 377f60d ([Build] ErrorCode 정돈, Location, StoreCategory, Store 의 id 자동 생성 되도록)
    private String locationId;

    @Column(name = "name", length = 20, nullable = false) // 이름
    private String name;

    @Column(name = "description", length = 50) // 설명
    private String description;

    @Builder
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    public Location(String name, String description) {
=======
    public Location(String locationId, String name, String description) {
        this.locationId = locationId;
>>>>>>> 7c82438 ([Refactor] 생성자 메서드 빌더 패턴 적용)
=======
    public Location(String name, String description) {
>>>>>>> 377f60d ([Build] ErrorCode 정돈, Location, StoreCategory, Store 의 id 자동 생성 되도록)
=======
    public Location(String name, String description) {
<<<<<<< HEAD
        this.locationId = UUID.randomUUID().toString();
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
=======
>>>>>>> a76aa9b ([Fix] UUID DB 자동 할당 방식으로 변경)
        this.name = name;
        this.description = description;
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    public static Location create(String name, String description) {
        return Location.builder()
=======
    public static Location create(String locationId, String name, String description) {
        return Location.builder()
                .locationId(locationId)
>>>>>>> 4d314db ([Build] Store를 생성하기 위한 Location, StoreCategory 정적팩토리 메서드 + ErrorCode 추가)
=======
    public static Location create(String name, String description) {
        return Location.builder()
>>>>>>> 377f60d ([Build] ErrorCode 정돈, Location, StoreCategory, Store 의 id 자동 생성 되도록)
=======
    public static Location create(String name, String description) {
        return Location.builder()
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
                .name(name)
                .description(description)
                .build();
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)

    public void update(String name, String description) {
        if (name != null) {
            this.name = name;
        }
        if (description != null) {
            this.description = description;
        }
    }
<<<<<<< HEAD
=======
>>>>>>> 7c82438 ([Refactor] 생성자 메서드 빌더 패턴 적용)
=======
>>>>>>> 4d314db ([Build] Store를 생성하기 위한 Location, StoreCategory 정적팩토리 메서드 + ErrorCode 추가)
=======
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
}
