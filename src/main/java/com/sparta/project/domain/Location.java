package com.sparta.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_location")
public class Location extends BaseEntity { // 지역

    @Id
    @Column(name="location_id", length=36, nullable=false, updatable=false)
    private String locationId;

    @Column(name="name", length=20, nullable=false) // 이름
    private String name;

    @Column(name="description", length=50) // 설명
    private String description;

    @Builder
    public Location(String name, String description) {
        this.locationId = UUID.randomUUID().toString();
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
