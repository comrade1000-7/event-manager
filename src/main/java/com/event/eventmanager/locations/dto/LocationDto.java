package com.event.eventmanager.locations.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocationDto(
        Long id,

        @NotBlank(message = "Specify name of location")
        String name,

        @NotBlank(message = "Specify address of location")
        String address,

        @NotNull
        @Min(value = 5, message = "Minimum capacity is 5")
        Long capacity,
        String description
) {

}
