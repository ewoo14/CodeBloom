package com.sparta.project.dto.location;

import jakarta.validation.constraints.NotNull;

public record LocationRequest (
        @NotNull String locationName,
        String description
) {}