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
<<<<<<< HEAD
<<<<<<< HEAD
	@JoinColumn(name="store_category_id", nullable=false)
	private StoreCategory storeCategory;
=======
	@JoinColumn(name="category_id", nullable=false)
	private Category category;
>>>>>>> 699e908 ([Feat] @OnDelete 설정 제거)
=======
	@JoinColumn(name="store_category_id", nullable=false)
	private StoreCategory storeCategory;
>>>>>>> 94cacd4 ([Style] category -> store_category로 변경)

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false)
	private Location location;

<<<<<<< HEAD

	@PrePersist
	public void setPrePersist() {
		this.status = StoreRequestStatus.WAITING;
	}

<<<<<<< HEAD
	public void approve() {
		this.status = StoreRequestStatus.APPROVE;
	}
=======
	public void updateStatus(StoreRequestStatus status) {
		this.status = status;
>>>>>>> eb1dc2e ([Feat] 음식점 생성 요청 반려 API)

	public void reject(String rejectionReason) {
		this.status = StoreRequestStatus.REJECT;
		this.rejectionReason = rejectionReason;
<<<<<<< HEAD
	}

	public void updateInfo(String name, String description, String address, StoreCategory category, Location location) {
		if (name!=null) this.name = name;
		if (description!=null) this.description = description;
		if (address!=null) this.address = address;
		if (category!=null) this.storeCategory = category;
		if (location!=null) this.location = location;
=======
>>>>>>> eb1dc2e ([Feat] 음식점 생성 요청 반려 API)
	}

	@Builder
	private StoreRequest(String id, String name, String description, String address, User owner, StoreCategory category, Location location) {
		this.storeRequestId = id;
=======
	@Builder
	public StoreRequest(String storeRequestId, String name, String description, String address, User owner, StoreCategory storeCategory, Location location) {
		this.storeRequestId = storeRequestId;
		this.isApproved = false;
>>>>>>> 7c82438 ([Refactor] 생성자 메서드 빌더 패턴 적용)
		this.name = name;
		this.description = description;
		this.address = address;
		this.owner = owner;
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
		this.category = category;
=======
		this.storeCategory = storeCategory;
>>>>>>> 94cacd4 ([Style] category -> store_category로 변경)
		this.location = location;
	}
>>>>>>> 7c82438 ([Refactor] 생성자 메서드 빌더 패턴 적용)
}