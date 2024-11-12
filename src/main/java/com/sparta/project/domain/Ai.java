package com.sparta.project.domain;

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 8965f75 ([Fix] UuidGenerator 활용 -> Menu, Ai, Location)
import com.sparta.project.util.UuidGenerator;
import jakarta.persistence.*;
import lombok.*;
<<<<<<< HEAD
=======
import jakarta.persistence.*;
import lombok.*;
<<<<<<< HEAD
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
>>>>>>> 55abe9a ([Style] "Chat" -> "Ai" 이름 변경)
=======
import java.util.UUID;
>>>>>>> 787c0ab ([Minor] ai_id UUID 정적 부여)
=======
>>>>>>> a76aa9b ([Fix] UUID DB 자동 할당 방식으로 변경)

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_ai")
public class Ai extends BaseEntity { // 채팅 기록
	@Id
<<<<<<< HEAD
<<<<<<< HEAD
	@GeneratedValue(strategy=GenerationType.UUID)
=======
>>>>>>> 55abe9a ([Style] "Chat" -> "Ai" 이름 변경)
=======
	@GeneratedValue(strategy = GenerationType.AUTO)
>>>>>>> a76aa9b ([Fix] UUID DB 자동 할당 방식으로 변경)
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
<<<<<<< HEAD
<<<<<<< HEAD
	private Ai(User user, String question, String answer) {
<<<<<<< HEAD
=======
	public Ai(String aiId, User user, String question, String answer) {
=======
	private Ai(String aiId, User user, String question, String answer) {
>>>>>>> fea02e7 ([Feat] AI Dto 및 service 구현)
		this.aiId = aiId;
>>>>>>> 55abe9a ([Style] "Chat" -> "Ai" 이름 변경)
=======
	private Ai(User user, String question, String answer) {
<<<<<<< HEAD
		this.aiId = UUID.randomUUID().toString();
>>>>>>> 787c0ab ([Minor] ai_id UUID 정적 부여)
=======
>>>>>>> a76aa9b ([Fix] UUID DB 자동 할당 방식으로 변경)
=======
		this.aiId = UuidGenerator.generateUuid();
>>>>>>> 8965f75 ([Fix] UuidGenerator 활용 -> Menu, Ai, Location)
		this.user = user;
		this.question = question;
		this.answer = answer;
	}

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> fea02e7 ([Feat] AI Dto 및 service 구현)
	public static Ai create(User user, String question, String answer) {
		return Ai.builder()
				.user(user)
				.question(question)
				.answer(answer)
				.build();
	}
<<<<<<< HEAD
=======
>>>>>>> 55abe9a ([Style] "Chat" -> "Ai" 이름 변경)
=======
>>>>>>> fea02e7 ([Feat] AI Dto 및 service 구현)
}
