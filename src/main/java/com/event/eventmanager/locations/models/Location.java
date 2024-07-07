package com.event.eventmanager.locations.models;

public record Location(
        Long id,
        String name,
        String address,
        Long capacity,
        String description
) {
}
