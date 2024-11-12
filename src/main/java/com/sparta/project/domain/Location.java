package com.sparta.project.domain;

import com.sparta.project.util.UuidGenerator;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_location")
public class Location extends BaseEntity { // 지역

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="location_id", length=36, nullable=false, updatable=false)
    private String locationId;

    @Column(name="name", length=20, nullable=false) // 이름
    private String name;

    @Column(name="description", length=50) // 설명
    private String description;

    @Builder
    public Location(String name, String description) {
        this.locationId = UuidGenerator.generateUuid();
        this.name = name;
        this.description = description;
    }

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
}
