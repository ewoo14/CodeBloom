package com.sparta.project.domain;

import com.sparta.project.domain.enums.OrderStatus;
import com.sparta.project.domain.enums.OrderType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Entity
@Table(name="p_order")
public class Order extends BaseEntity { // 주문
	@Id
	@Column(name="order_id", length=36, nullable=false, updatable=false)
	private String orderId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@Column(name="type", nullable=false) // 주문 유형 (온라인/오프라인)
	@Enumerated(EnumType.STRING)
	private OrderType type;

	@Column(name="status", nullable=false) // 주문 상태 (대기/승인/취소)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@Column(name="order_price", nullable=false) // 주문 가격
	private Integer orderPrice;

	@OneToMany(mappedBy="order")
	private List<OrderFood> orderFoods = new ArrayList<>();

}