package com.event.eventmanager.locations.controller;

import com.event.eventmanager.locations.dto.LocationDto;
import com.event.eventmanager.locations.service.Location;
import com.event.eventmanager.locations.service.LocationService;
import com.event.eventmanager.locations.utils.LocationDtoMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
@Slf4j
public class LocationController {

    private final LocationService locationService;
    private final LocationDtoMapper dtoMapper;


    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        log.info("Get request for getAllLocations");
        List<Location> locationList = locationService.getAllLocations();
        return ResponseEntity.ok(locationList.stream().map(dtoMapper::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(
            @RequestBody @Valid LocationDto locationDto
    ) {
        log.info("Get request for createLocation: locationDto={}", locationDto);
        var createdLocation = locationService.createLocation(dtoMapper.toDomain(locationDto));
        return ResponseEntity
                .status(201)
                .body(dtoMapper.toDto(createdLocation));
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<LocationDto> deleteLocation(
            @PathVariable Long locationId
    ) {
        log.info("Get request for deleteLocation: locationId={}", locationId);
        var deleteLocation = locationService.deleteLocation(locationId);
        return ResponseEntity
                .status(204)
                .body(dtoMapper.toDto(deleteLocation));
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDto> getLocation(
            @PathVariable Long locationId
    ) {
        log.info("Get request for getLocation: locationId={}", locationId);
        Location locationById = locationService.getLocationById(locationId);
        return ResponseEntity.ok(dtoMapper.toDto(locationById));
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<LocationDto> updateLocation(
            @PathVariable Long locationId,
            @RequestBody @Valid LocationDto updateLocationDto
    ) {
        log.info("Get request for updateLocation: locationId={}, newLocationDto={}",
                locationId, updateLocationDto);
        Location updateLocation = locationService.updateLocation(locationId, dtoMapper.toDomain(updateLocationDto));
        return ResponseEntity.ok(dtoMapper.toDto(updateLocation));
    }

}
