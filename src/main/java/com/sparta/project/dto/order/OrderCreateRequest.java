package com.sparta.project.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record OrderCreateRequest(
    @NotNull List<MenuInfo> menus,
    @NotBlank String addressId,
    @NotBlank String storeId,
    @NotBlank String demand,
    @NotBlank String type
) {
    public record MenuInfo(@NotBlank String menuId, @NotNull @Positive Integer count) {}
}

