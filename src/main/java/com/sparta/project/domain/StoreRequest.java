package com.sparta.project.domain;

import com.sparta.project.domain.enums.StoreRequestStatus;
import com.sparta.project.util.UuidGenerator;
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
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="store_request_id", length=36, nullable=false, updatable=false)
	private String storeRequestId;

	@Column(name="status", nullable=false) // 승인 여부
	@Enumerated(EnumType.STRING)
	private StoreRequestStatus status;

	@Column(name="name", length=50, nullable=false) // 음식점 이름
	private String name;

	@Column(name="description", columnDefinition = "TEXT") // 음식점 설명
	private String description;

	@Column(name="address", length=255) // 음식점 주소
	private String address;

	@Column(name="rejection_reason", length=2048)
	private String rejectionReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="owner_id", nullable=false)
	private User owner;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="store_category_id", nullable=false)
	private StoreCategory storeCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false)
	private Location location;


	@PrePersist
	public void setPrePersist() {
		this.status = StoreRequestStatus.WAITING;
	}

	public void approve() {
		this.status = StoreRequestStatus.APPROVE;
	}

	public void reject(String rejectionReason) {
		this.status = StoreRequestStatus.REJECT;
		this.rejectionReason = rejectionReason;
	}

	public void updateInfo(String name, String description, String address, StoreCategory category, Location location) {
		if (name!=null) this.name = name;
		if (description!=null) this.description = description;
		if (address!=null) this.address = address;
		if (category!=null) this.storeCategory = category;
		if (location!=null) this.location = location;
	}

	@Builder
	private StoreRequest(String id, String name, String description, String address, User owner, StoreCategory category, Location location) {
		this.storeRequestId = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.owner = owner;
		this.storeCategory = category;
		this.location = location;
	}

	public static StoreRequest create(String name, String description, String address, User owner, StoreCategory storeCategory, Location location) {
		return StoreRequest.builder()
				.id(UuidGenerator.generateUuid())
				.name(name)
				.description(description)
				.address(address)
				.owner(owner)
				.category(storeCategory)
				.location(location)
				.build();
	}
}