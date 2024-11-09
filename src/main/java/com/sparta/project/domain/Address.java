package com.sparta.project.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_address")
public class Address extends BaseEntity { // 배송지
	@Id
	@Column(name="address_id", length=36, nullable=false, updatable=false)
	private String addressId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	@Column(name="city", length=20) // 도시
	private String city;

	@Column(name="district", length=50) // 시군구
	private String district;

	@Column(name="street_name", length=20) // 도로명
	private String streetName;

	@Column(name="street_number") // 도로명 코드
	private Integer streetNumber;

	@Column(name="detail") // 상세 주소
	private String detail;

	@Column(name="is_default") // 메인 주소지 여부
	private Boolean isDefault;

	@Builder
	public Address(String addressId, User user, String city, String district, String streetName, Integer streetNumber, String detail, Boolean isDefault) {
		this.addressId = addressId;
		this.user = user;
		this.city = city;
		this.district = district;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.detail = detail;
		this.isDefault = isDefault;
	}

}
