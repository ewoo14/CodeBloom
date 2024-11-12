package com.sparta.project.dto.address;


public record AddressUpdateRequest(
        String city,
        String district,
        String streetName,
        String streetNumber,
        String detail,
        Boolean isDefault
) {
}
