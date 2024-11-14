package com.sparta.project.dto.address;

import com.sparta.project.domain.Address;
import java.time.LocalDateTime;

public record AddressAdminResponse(
        String AddressId,
        String city,
        String district,
        String streetName,
        String streetNumber,
        String detail,
        Boolean isDefault,
        Long addressOwnerId,
        Boolean isDeleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        LocalDateTime deletedAt,
        String deletedBy
) {
    public static AddressAdminResponse from(final Address address) {
        return new AddressAdminResponse(
                address.getAddressId(),
                address.getCity(),
                address.getDistrict(),
                address.getStreetName(),
                address.getStreetNumber(),
                address.getDetail(),
                address.getIsDefault(),
                address.getUser().getUserId(),
                address.getIsDeleted(),
                address.getCreatedAt(),
                address.getCreatedBy(),
                address.getUpdatedAt(),
                address.getUpdatedBy(),
                address.getDeletedAt(),
                address.getDeletedBy()
        );
    }
}
