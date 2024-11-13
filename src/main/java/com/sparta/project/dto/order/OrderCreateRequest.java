package com.sparta.project.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
<<<<<<< HEAD
=======
import jakarta.validation.constraints.Positive;
>>>>>>> c1fc115 ([Feat] 주문 요청)

import java.util.List;

public record OrderCreateRequest(
<<<<<<< HEAD
    @NotNull List<OrderMenuInfo> menus,
=======
    @NotNull List<MenuInfo> menus,
>>>>>>> c1fc115 ([Feat] 주문 요청)
    @NotBlank String addressId,
    @NotBlank String storeId,
    @NotBlank String demand,
    @NotBlank String type
<<<<<<< HEAD
) {}
=======
) {
    public record MenuInfo(@NotBlank String menuId, @NotNull @Positive Integer count) {}
}
>>>>>>> c1fc115 ([Feat] 주문 요청)

