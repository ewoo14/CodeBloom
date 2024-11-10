package com.sparta.project.dto.menu;

<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.domain.Menu;

=======
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
import com.sparta.project.domain.Menu;

>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
public record MenuResponse(
        String menuId,
        String storeId,
        String name,
        String description,
        Integer price,
        Boolean isClosed
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
) {
    public static MenuResponse from(Menu menu) {
        return new MenuResponse(
                menu.getMenuId(),
                menu.getStore().getStoreId(),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice(),
                menu.getIsClosed()
        );
    }
<<<<<<< HEAD
}
=======
) {}
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
}
>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
