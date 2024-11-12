package com.sparta.project.dto.location;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Valid
public record LocationRequest (
        @NotBlank String locationName,
        String description
) {}