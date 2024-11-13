package com.sparta.project.dto.address;


import com.sparta.project.domain.Address;
import java.time.LocalDateTime;

public record AddressResponse(
        String AddressId,
        String city,
        String district,
        String streetName,
        String streetNumber,
        String detail,
        Boolean isDefault,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static AddressResponse from(final Address address) {
        return new AddressResponse(
                address.getAddressId(),
                address.getCity(),
                address.getDistrict(),
                address.getStreetName(),
                address.getStreetNumber(),
                address.getDetail(),
                address.getIsDefault(),
                address.getCreatedAt(),
                address.getUpdatedAt()
        );
    }
}
