package com.sparta.project.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderCreateRequest(
    @NotNull List<OrderMenuInfo> menus,
    @NotBlank String addressId,
    @NotBlank String storeId,
    @NotBlank String demand,
    @NotBlank String type
) {}

