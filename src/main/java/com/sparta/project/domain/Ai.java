package com.sparta.project.domain;

import com.sparta.project.util.UuidGenerator;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_ai")
public class Ai extends BaseEntity { // 채팅 기록
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ai_id", length=36, nullable=false, updatable=false)
	private String aiId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	@Column(name="question", columnDefinition = "TEXT") // 질문
	private String question;

	@Column(name="answer", columnDefinition = "TEXT") // 답변
	private String answer;

	@Builder
	private Ai(User user, String question, String answer) {
		this.aiId = UuidGenerator.generateUuid();
		this.user = user;
		this.question = question;
		this.answer = answer;
	}

	public static Ai create(User user, String question, String answer) {
		return Ai.builder()
				.user(user)
				.question(question)
				.answer(answer)
				.build();
	}
}
