package com.sparta.project.domain;

import com.sparta.project.util.UuidGenerator;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_menu")
public class Menu extends BaseEntity { // 메뉴
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="menu_id", length=36, nullable=false, updatable=false)
	private String menuId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	private Store store;

	@Column(name="name", length=50, nullable=false) // 이름
	private String name;

	@Column(name="description", length=100) // 설명
	private String description;

	@Column(name="price", nullable=false) // 가격
	private Integer price;

	@Column(name="is_closed", nullable=false) // 숨김 여부
	private Boolean isClosed;

	@Builder
	private Menu(Store store, String name, String description, Integer price, Boolean isClosed) {
		this.store = store;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isClosed = isClosed;
	}

	public static Menu create(String name, Store store, String description, Integer price, Boolean isClosed) {
		return Menu.builder()
				.store(store)
				.name(name)
				.description(description)
				.price(price)
				.isClosed(isClosed)
				.build();
	}

	public void update(String name, String description, Integer price, Boolean isClosed) {
		if (name != null) {
			this.name = name;
		}
		if (description != null) {
			this.description = description;
		}
		if (price != null) {
			this.price = price;
		}
		if (isClosed != null) {
			this.isClosed = isClosed;
		}
	}
}