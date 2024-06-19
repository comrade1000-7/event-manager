package com.event.eventmanager.locations.utils;

import com.event.eventmanager.locations.dto.LocationDto;
import com.event.eventmanager.locations.entities.LocationEntity;
import com.event.eventmanager.locations.service.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationEntityMapper {
    public Location toDomain(LocationEntity locationEntity) {
        return new Location(
                locationEntity.getId(),
                locationEntity.getName(),
                locationEntity.getAddress(),
                locationEntity.getCapacity(),
                locationEntity.getDescription()
        );
    }

    public LocationEntity toEntity(Location location) {
        return new LocationEntity(
                location.id(),
                location.name(),
                location.address(),
                location.capacity(),
                location.description()
        );
    }
}
