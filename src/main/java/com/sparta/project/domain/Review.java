package com.sparta.project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_review")
public class Review extends BaseEntity { // 리뷰
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="review_id", length=36, nullable=false, updatable=false)
	private String reviewId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	private Store store;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id", nullable=false)
	private Order order;

	@Column(name="content", length=1000) // 리뷰 내용
	private String content;

	@Min(1)
	@Max(5)
	@Column(name="score") // 평점
	private Integer score;

	@Builder
	public Review(User user, Store store, Order order, String content, Integer score) {
		this.user = user;
		this.store = store;
		this.order = order;
		this.content = content;
		this.score = score;
	}

	public static Review create(User user, Store store, Order order, String content, Integer score) {
		return Review.builder()
				.user(user)
				.store(store)
				.order(order)
				.content(content)
				.score(score)
				.build();
	}

	public void update(String content, Integer score) {
		if (content != null) {
			this.content = content;
		}
		if (score != null) {
			this.score = score;
		}
	}
}