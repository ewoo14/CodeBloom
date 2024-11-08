package com.sparta.project.domain;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
=======
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
>>>>>>> 0ac8d3c ([Style] Food -> Menu 이름 변경)

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="p_order_menu")
public class OrderMenu extends BaseEntity { // 주문-메뉴
	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="order_menu_id", length=36, nullable=false, updatable=false)
=======
	@Column(name="order_menu", length=36, nullable=false, updatable=false)
>>>>>>> 0ac8d3c ([Style] Food -> Menu 이름 변경)
	private String orderMenuId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="menu_id", nullable=false)
<<<<<<< HEAD
=======
	@OnDelete(action = OnDeleteAction.CASCADE)
>>>>>>> 0ac8d3c ([Style] Food -> Menu 이름 변경)
	private Menu menu;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id", nullable=false)
<<<<<<< HEAD
=======
	@OnDelete(action = OnDeleteAction.CASCADE)
>>>>>>> 0ac8d3c ([Style] Food -> Menu 이름 변경)
	private Order order;

	@Column(name="count", nullable=false) // 해당 메뉴 주문 개수
	private Integer count;

	@Builder
<<<<<<< HEAD
	public OrderMenu(Menu menu, Order order, Integer count) {
=======
	public OrderMenu(String orderMenuId, Menu menu, Order order, Integer count) {
		this.orderMenuId = orderMenuId;
>>>>>>> 0ac8d3c ([Style] Food -> Menu 이름 변경)
		this.menu = menu;
		this.order = order;
		this.count = count;
	}

<<<<<<< HEAD
	public static OrderMenu create(Menu menu, Order order, Integer count) {
		return OrderMenu.builder().menu(menu).order(order).count(count).build();
	}

=======
>>>>>>> 0ac8d3c ([Style] Food -> Menu 이름 변경)
}