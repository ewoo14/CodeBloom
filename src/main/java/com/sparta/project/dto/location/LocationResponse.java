package com.sparta.project.dto.location;

import com.sparta.project.domain.Location;

public record LocationResponse (
        String locationId,
        String locationName,
        String description
) {
    public static LocationResponse from(Location location) {
        return new LocationResponse(
                location.getLocationId(),
                location.getName(),
                location.getDescription()
        );
    }
}
