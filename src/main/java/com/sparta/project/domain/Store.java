package com.sparta.project.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name = "p_store")
public class Store extends BaseEntity { // 음식점
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "store_id", length = 36, nullable = false, updatable = false)
    private String storeId;

    @Column(name = "name", length = 50, nullable = false) // 음식점 이름
    private String name;

    @Column(name = "description", columnDefinition = "TEXT") // 음식점 설명
    private String description;

    @Column(name = "address", length = 255) // 음식점 주소
    private String address;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_category_id", nullable = false)
    private StoreCategory storeCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
=======
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="owner_id", nullable=false)
	private User owner;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="store_category_id", nullable=false)
	private StoreCategory storeCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false)
	private Location location;
>>>>>>> 699e908 ([Feat] @OnDelete 설정 제거)

<<<<<<< HEAD
    @Column(name = "score") // 음식점 리뷰 평균 평점
    private Double score; // 빌더에 넣지 않았습니다.

    @Builder
    public Store(String name, String description, String address,
                 User owner, StoreCategory storeCategory, Location location, Double score) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.owner = owner;
        this.storeCategory = storeCategory;
        this.location = location;
        this.score = score;
    }

    public static Store create(String name, String description, String address,
                               User owner, StoreCategory storeCategory, Location location, Double score) {
        return Store.builder()
                .name(name)
                .description(description)
                .address(address)
                .owner(owner)
                .storeCategory(storeCategory)
                .location(location)
                .score(score)
                .build();
    }

    public static Store create(String name, String description, String address,
                               User owner, StoreCategory storeCategory, Location location) {
        return Store.create(name, description, address, owner, storeCategory, location, 0.0);
    }


    public void updateScore(Double score) {
        this.score = score;
    }

    public void update(String storeName, String description, Location location, StoreCategory storeCategory) {
        if (storeName != null) this.name = storeName;
        if (description != null) this.description = description;
        if (location != null) this.location = location;
        if (storeCategory != null) this.storeCategory = storeCategory;

    }
=======
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

>>>>>>> 7c82438 ([Refactor] 생성자 메서드 빌더 패턴 적용)
}