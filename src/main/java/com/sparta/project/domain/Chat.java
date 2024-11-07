package com.sparta.project.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_chat")
public class Chat extends BaseEntity { // 채팅 기록
	@Id
	@Column(name="chat_id", length=36, nullable=false, updatable=false)
	private String chatId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@Column(name="question", columnDefinition = "TEXT") // 질문
	private String question;

	@Column(name="answer", columnDefinition = "TEXT") // 답변
	private String answer;

	@Builder
	public Chat(String chatId, User user, String question, String answer) {
		this.chatId = chatId;
		this.user = user;
		this.question = question;
		this.answer = answer;
	}

}
