package com.event.eventmanager.locations.service;

public record Location(
        Long id,
        String name,
        String address,
        Long capacity,
        String description
) {
}
