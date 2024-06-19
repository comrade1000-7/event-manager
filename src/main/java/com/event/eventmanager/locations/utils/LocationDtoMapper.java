package com.event.eventmanager.locations.utils;

import com.event.eventmanager.locations.dto.LocationDto;
import com.event.eventmanager.locations.service.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationDtoMapper {

    public Location toDomain(LocationDto locationDto) {
        return new Location(
                locationDto.id(),
                locationDto.name(),
                locationDto.address(),
                locationDto.capacity(),
                locationDto.description()
        );
    }

    public LocationDto toDto(Location location) {
        return new LocationDto(
                location.id(),
                location.name(),
                location.address(),
                location.capacity(),
                location.description()
        );
    }
}