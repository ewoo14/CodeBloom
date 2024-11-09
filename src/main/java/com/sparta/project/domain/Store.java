package com.sparta.project.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_store")
public class Store extends BaseEntity { // 음식점
	@Id
	@Column(name="store_id", length=36, nullable=false, updatable=false)
	private String storeId;

	@Column(name="name", length=50, nullable=false) // 음식점 이름
	private String name;

	@Column(name="description", columnDefinition = "TEXT") // 음식점 설명
	private String description;

	@Column(name="address", length=255) // 음식점 주소
	private String address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="owner_id", nullable=false)
	private User owner;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="store_category_id", nullable=false)
	private StoreCategory storeCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false)
	private Location location;

	@Column(name="score") // 음식점 리뷰 평균 평점
	private Double score; // 빌더에 넣지 않았습니다.

	@Builder
	public Store(String storeId, String name, String description, String address, User owner, StoreCategory storeCategory, Location location) {
		this.storeId = storeId;
		this.name = name;
		this.description = description;
		this.address = address;
		this.owner = owner;
		this.storeCategory = storeCategory;
		this.location = location;
	}

	public void updateScore(Double score) {
		this.score = score;
	}

}