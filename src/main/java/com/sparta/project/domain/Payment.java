package com.sparta.project.domain;

import com.sparta.project.domain.enums.OrderType;
import com.sparta.project.domain.enums.PaymentType;
import com.sparta.project.domain.enums.PgName;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_payment")
public class Payment extends BaseEntity { // 결제
	@Id
	@Column(name="payment_id", length=36, nullable=false, updatable=false)
	private String paymentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id", nullable=false)
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	@Column(name="type", nullable=false) // 결제 방식 (카드)
	@Enumerated(EnumType.STRING)
	private PaymentType type;

	@Column(name="payment_price", nullable=false) // 결제 가격
	private Integer paymentPrice;

	@Column(name="pg_name", length=100) // PG사 이름 (NHN KCP/KG이니시스/토스페이먼츠)
	@Enumerated(EnumType.STRING)
	private PgName pgName;

	@Column(name="pg_key", length=255) // PG사 결제 코드
	private String pgKey;

	@Builder
	public Payment(String paymentId, Order order, User user, PaymentType type, Integer paymentPrice, PgName pgName, String pgKey) {
		this.paymentId = paymentId;
		this.order = order;
		this.user = user;
		this.type = type;
		this.paymentPrice = paymentPrice;
		this.pgName = pgName;
		this.pgKey = pgKey;
	}

}