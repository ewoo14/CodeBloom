package com.sparta.project.dto.menu;

<<<<<<< HEAD
import com.sparta.project.domain.Menu;

=======
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
public record MenuResponse(
        String menuId,
        String storeId,
        String name,
        String description,
        Integer price,
        Boolean isClosed
<<<<<<< HEAD
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
}
=======
) {}
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
