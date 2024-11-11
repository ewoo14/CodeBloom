package com.sparta.project.domain;

import com.sparta.project.domain.enums.PaymentType;
import com.sparta.project.domain.enums.PgName;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_payment")
public class Payment extends BaseEntity { // 결제
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="payment_id", length=36, nullable=false, updatable=false)
	private String paymentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id", nullable=false)
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
<<<<<<< HEAD
<<<<<<< HEAD
=======
	@OnDelete(action = OnDeleteAction.CASCADE)
>>>>>>> 6ad0ce7 ([Feat] Order FK로 Address, Store 추가, Payment FK로 User 추가)
=======
>>>>>>> 699e908 ([Feat] @OnDelete 설정 제거)
	private User user;

	@Column(name="type", nullable=false) // 결제 방식 (카드)
	@Enumerated(EnumType.STRING)
	private PaymentType type;

	@Column(name="payment_price", nullable=false) // 결제 가격
	private Integer paymentPrice;

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> b1c6552 ([Feat] Payment에 PG사 이름, PG사 결제 코드 추가)
	@Column(name="pg_name", length=100) // PG사 이름 (NHN KCP/KG이니시스/토스페이먼츠)
	@Enumerated(EnumType.STRING)
	private PgName pgName;

	@Column(name="pg_key", length=255) // PG사 결제 코드
	private String pgKey;

<<<<<<< HEAD
	@Builder
<<<<<<< HEAD
<<<<<<< HEAD
	private Payment(Order order, User user, PaymentType type, Integer paymentPrice, PgName pgName, String pgKey) {
=======
	public Payment(String paymentId, Order order, User user, PaymentType type, Integer paymentPrice, PgName pgName, String pgKey) {
=======
	private Payment(String paymentId, Order order, User user, PaymentType type, Integer paymentPrice, PgName pgName, String pgKey) {
>>>>>>> 054108d (결제 기능 구현 Service)
		this.paymentId = paymentId;
>>>>>>> 6ad0ce7 ([Feat] Order FK로 Address, Store 추가, Payment FK로 User 추가)
		this.order = order;
		this.user = user;
		this.type = type;
		this.paymentPrice = paymentPrice;
		this.pgName = pgName;
		this.pgKey = pgKey;
	}

<<<<<<< HEAD
<<<<<<< HEAD
	public static Payment create(Order order, User user, PaymentType type, Integer paymentPrice, PgName pgName) {
		return Payment.builder()
<<<<<<< HEAD
				.order(order)
				.user(user)
				.type(type)
=======
	public static Payment create(Order order, User user, String type, Integer paymentPrice, PgName pgName) {
=======
	public static Payment create(Order order, User user, PaymentType type, Integer paymentPrice, PgName pgName) {
>>>>>>> edbb19b ([Refactor] PgName, PaymentType of 메서드 만들어서 유효성검증과 생성 한꺼번에)
		return Payment.builder()
				.paymentId(UuidGenerator.generateUuid())
=======
>>>>>>> 23607d5 ([Refactor] UuidGenerator 삭제, Payment 엔티티에 @GeneratedValue(strategy = GenerationType.UUID) 추가)
				.order(order)
				.user(user)
<<<<<<< HEAD
				.type(PaymentType.valueOf(type))
>>>>>>> 054108d (결제 기능 구현 Service)
=======
				.type(type)
>>>>>>> edbb19b ([Refactor] PgName, PaymentType of 메서드 만들어서 유효성검증과 생성 한꺼번에)
				.paymentPrice(paymentPrice)
				.pgName(pgName)
				.pgKey(pgName.getPgKey())
				.build();
<<<<<<< HEAD
=======
=======
>>>>>>> b1c6552 ([Feat] Payment에 PG사 이름, PG사 결제 코드 추가)
	@Builder
	public Payment(String paymentId, Order order, PaymentType type, Integer paymentPrice, PgName pgName, String pgKey) {
		this.paymentId = paymentId;
		this.order = order;
		this.type = type;
		this.paymentPrice = paymentPrice;
<<<<<<< HEAD
>>>>>>> 7c82438 ([Refactor] 생성자 메서드 빌더 패턴 적용)
=======
		this.pgName = pgName;
		this.pgKey = pgKey;
>>>>>>> b1c6552 ([Feat] Payment에 PG사 이름, PG사 결제 코드 추가)
=======
>>>>>>> 054108d (결제 기능 구현 Service)
	}

}