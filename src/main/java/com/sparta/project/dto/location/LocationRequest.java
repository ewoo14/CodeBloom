package com.sparta.project.dto.location;

import jakarta.validation.constraints.NotBlank;

public record LocationRequest (
        @NotBlank String locationName,
        String description
) {}