package com.sparta.project.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_menu")
public class Menu extends BaseEntity { // 메뉴
	@Id
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
	public Menu(String menuId, Store store, String name, String description, Integer price, Boolean isClosed) {
		this.menuId = menuId;
		this.store = store;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isClosed = isClosed;
	}

}