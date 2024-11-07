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
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="location_id", length=36, nullable=false, updatable=false)
    private String locationId;

    @Column(name = "name", length = 20, nullable = false) // 이름
    private String name;

    @Column(name = "description", length = 50) // 설명
    private String description;

    @Builder
<<<<<<< HEAD
    public Location(String name, String description) {
=======
    public Location(String locationId, String name, String description) {
        this.locationId = locationId;
>>>>>>> 7c82438 ([Refactor] 생성자 메서드 빌더 패턴 적용)
        this.name = name;
        this.description = description;
    }

<<<<<<< HEAD
    public static Location create(String name, String description) {
        return Location.builder()
                .name(name)
                .description(description)
                .build();
    }

    public void update(String name, String description) {
        if (name != null) {
            this.name = name;
        }
        if (description != null) {
            this.description = description;
        }
    }
=======
>>>>>>> 7c82438 ([Refactor] 생성자 메서드 빌더 패턴 적용)
}
