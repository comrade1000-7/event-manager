package com.event.eventmanager.locations.service;

import com.event.eventmanager.locations.models.Location;
import com.event.eventmanager.locations.repository.LocationRepository;
import com.event.eventmanager.locations.utils.LocationEntityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {

    private final LocationRepository repository;
    private final LocationEntityMapper entityMapper;

    public List<Location> getAllLocations() {
        return repository.findAll().stream().map(entityMapper::toDomain).toList();
    }

    public Location createLocation(Location locationToCreate) {
        if (locationToCreate.id() != null) {
            throw new IllegalArgumentException("Can not create location. Don't specify the id");
        }
        var creationLocation = repository.save(entityMapper.toEntity(locationToCreate));
        return entityMapper.toDomain(creationLocation);
    }

    public Location deleteLocation(Long locationId) {
        var entityToDelete = repository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location with id %s not found "
                        .formatted(locationId)));
        repository.deleteById(locationId);
        return entityMapper.toDomain(entityToDelete);
    }

    public Location getLocationById(Long locationId) {
        var entity = repository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location with id %s not found "
                        .formatted(locationId)));
        return entityMapper.toDomain(entity);
    }

    public Location updateLocation(Long locationId, Location locationToUpdate) {
        if (locationToUpdate.id() != null) {
            throw new IllegalArgumentException("Can not update location. Don't specify the id");
        }
        var entityToUpdate = repository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location with id %s not found "
                        .formatted(locationId)));
        entityToUpdate.setAddress(locationToUpdate.address());
        entityToUpdate.setName(locationToUpdate.address());
        entityToUpdate.setCapacity(locationToUpdate.capacity());
        entityToUpdate.setDescription(locationToUpdate.description());

        var updatedEntity = repository.save(entityToUpdate);

        return entityMapper.toDomain(updatedEntity);
    }


}
