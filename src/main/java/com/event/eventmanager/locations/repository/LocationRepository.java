package com.event.eventmanager.locations.repository;

import com.event.eventmanager.locations.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
}
