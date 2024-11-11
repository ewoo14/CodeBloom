package com.sparta.project.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressCreateRequest(
        @NotBlank String city,
        @NotBlank String district,
        @NotBlank String streetName,
        @NotBlank String streetNumber,
        @NotBlank String detail,
        @NotNull Boolean isDefault
) {
}
