package com.sparta.project.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="p_order_menu")
public class OrderMenu extends BaseEntity { // 주문-메뉴
	@Id
	@Column(name="order_menu", length=36, nullable=false, updatable=false)
	private String orderMenuId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="menu_id", nullable=false)
	private Menu menu;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id", nullable=false)
	private Order order;

	@Column(name="count", nullable=false) // 해당 메뉴 주문 개수
	private Integer count;

	@Builder
	public OrderMenu(String orderMenuId, Menu menu, Order order, Integer count) {
		this.orderMenuId = orderMenuId;
		this.menu = menu;
		this.order = order;
		this.count = count;
	}

}