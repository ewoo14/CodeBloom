package com.sparta.project.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="p_order_food")
public class OrderFood extends BaseEntity { // 주문-음식
	@Id
	@Column(name="order_food", length=36, nullable=false, updatable=false)
	private String orderFoodId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="food_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Food food;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Order order;

	@Column(name="count", nullable=false) // 해당 음식 주문 개수
	private Integer count;

	@Builder
	public OrderFood(String orderFoodId, Food food, Order order, Integer count) {
		this.orderFoodId = orderFoodId;
		this.food = food;
		this.order = order;
		this.count = count;
	}

}