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
@Setter
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
	private Menu(Store store, String name, String description, Integer price, Boolean isClosed) {
=======
	public Menu(String menuId, Store store, String name, String description, Integer price, Boolean isClosed) {
		this.menuId = menuId;
>>>>>>> 0ac8d3c ([Style] Food -> Menu 이름 변경)
		this.store = store;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isClosed = isClosed;
	}

<<<<<<< HEAD
	public static Menu create(String name, Store store, String description, Integer price, Boolean isClosed) {
		return Menu.builder()
				.store(store)
				.name(name)
				.description(description)
				.price(price)
				.isClosed(isClosed)
				.build();
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
=======
>>>>>>> 0ac8d3c ([Style] Food -> Menu 이름 변경)
}