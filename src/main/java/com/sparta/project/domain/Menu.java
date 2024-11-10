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
>>>>>>> 0ac8d3c ([Style] Food -> Menu 이름 변경)

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_menu")
public class Menu extends BaseEntity { // 메뉴
	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy=GenerationType.UUID)
=======
>>>>>>> 0ac8d3c ([Style] Food -> Menu 이름 변경)
	@Column(name="menu_id", length=36, nullable=false, updatable=false)
	private String menuId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
<<<<<<< HEAD
<<<<<<< HEAD
=======
	@OnDelete(action = OnDeleteAction.CASCADE)
>>>>>>> 0ac8d3c ([Style] Food -> Menu 이름 변경)
=======
>>>>>>> 699e908 ([Feat] @OnDelete 설정 제거)
	private Store store;

	@Column(name="name", length=50, nullable=false) // 이름
	private String name;

	@Column(name="description", length=100) // 설명
	private String description;

	@Column(name="price", nullable=false) // 가격
	private Integer price;

	@Column(name="is_closed", nullable=false) // 숨김 여부
	private Boolean isClosed;

	@Builder
<<<<<<< HEAD
<<<<<<< HEAD
	private Menu(Store store, String name, String description, Integer price, Boolean isClosed) {
=======
	public Menu(String menuId, Store store, String name, String description, Integer price, Boolean isClosed) {
=======
	private Menu(String menuId, Store store, String name, String description, Integer price, Boolean isClosed) {
>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
		this.menuId = menuId;
>>>>>>> 0ac8d3c ([Style] Food -> Menu 이름 변경)
		this.store = store;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isClosed = isClosed;
	}

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	public static Menu create(String name, Store store, String description, Integer price, Boolean isClosed) {
		return Menu.builder()
				.store(store)
				.name(name)
=======
	public static Menu create(String name, Store store,String description, Integer price, Boolean isClosed) {
=======
	public static Menu create(String name, Store store, String description, Integer price, Boolean isClosed) {
>>>>>>> fea02e7 ([Feat] AI Dto 및 service 구현)
		return Menu.builder()
				.name(name)
				.store(store)
>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
				.description(description)
				.price(price)
				.isClosed(isClosed)
				.build();
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
=======
>>>>>>> fea02e7 ([Feat] AI Dto 및 service 구현)
	}

	public void update(String name, String description, Integer price, Boolean isClosed) {
		if (name != null) {
			this.name = name;
		}
		if (description != null) {
			this.description = description;
		}
		if (price != null) {
			this.price = price;
		}
		if (isClosed != null) {
			this.isClosed = isClosed;
		}
	}
<<<<<<< HEAD
=======
>>>>>>> 0ac8d3c ([Style] Food -> Menu 이름 변경)
=======
>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
}