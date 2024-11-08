package com.sparta.project.domain;

<<<<<<< HEAD
import com.sparta.project.util.UuidGenerator;
import jakarta.persistence.*;
import lombok.*;
=======
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
>>>>>>> 55abe9a ([Style] "Chat" -> "Ai" 이름 변경)

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_ai")
public class Ai extends BaseEntity { // 채팅 기록
	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy=GenerationType.UUID)
=======
>>>>>>> 55abe9a ([Style] "Chat" -> "Ai" 이름 변경)
	@Column(name="ai_id", length=36, nullable=false, updatable=false)
	private String aiId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
<<<<<<< HEAD
<<<<<<< HEAD
=======
	@OnDelete(action = OnDeleteAction.CASCADE)
>>>>>>> 55abe9a ([Style] "Chat" -> "Ai" 이름 변경)
=======
>>>>>>> 699e908 ([Feat] @OnDelete 설정 제거)
	private User user;

	@Column(name="question", columnDefinition = "TEXT") // 질문
	private String question;

	@Column(name="answer", columnDefinition = "TEXT") // 답변
	private String answer;

	@Builder
<<<<<<< HEAD
	private Ai(User user, String question, String answer) {
=======
	public Ai(String aiId, User user, String question, String answer) {
		this.aiId = aiId;
>>>>>>> 55abe9a ([Style] "Chat" -> "Ai" 이름 변경)
		this.user = user;
		this.question = question;
		this.answer = answer;
	}

<<<<<<< HEAD
	public static Ai create(User user, String question, String answer) {
		return Ai.builder()
				.user(user)
				.question(question)
				.answer(answer)
				.build();
	}
=======
>>>>>>> 55abe9a ([Style] "Chat" -> "Ai" 이름 변경)
}
