package com.sparta.project.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_store_request")
public class StoreRequest extends BaseEntity { // 음식점 허가 요청
	@Id
	@Column(name="store_request_id", length=36, nullable=false, updatable=false)
	private String storeRequestId;

	@Column(name="is_approved", nullable=false) // 승인 여부
	@ColumnDefault("false")
	private Boolean isApproved;

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

	@Builder
	public StoreRequest(String storeRequestId, String name, String description, String address, User owner, StoreCategory storeCategory, Location location) {
		this.storeRequestId = storeRequestId;
		this.isApproved = false;
		this.name = name;
		this.description = description;
		this.address = address;
		this.owner = owner;
		this.storeCategory = storeCategory;
		this.location = location;
	}
}