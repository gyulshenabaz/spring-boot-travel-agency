package org.fmiplovdiv.travelagency.travelagency.controllers;

import org.fmiplovdiv.travelagency.travelagency.dtos.CreateLocationDTO;
import org.fmiplovdiv.travelagency.travelagency.dtos.ResponseLocationDTO;
import org.fmiplovdiv.travelagency.travelagency.dtos.UpdateLocationDTO;
import org.fmiplovdiv.travelagency.travelagency.services.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseLocationDTO>> getAllLocations() {
        List<ResponseLocationDTO> response = locationService.getAllLocations();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<ResponseLocationDTO> getLocationById(@PathVariable("locationId") Long id) {
        ResponseLocationDTO response = locationService.getLocationById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseLocationDTO> createLocation(@RequestBody CreateLocationDTO toCreate) {
        ResponseLocationDTO response = locationService.createLocation(toCreate);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ResponseLocationDTO> updateLocation(@RequestBody UpdateLocationDTO toUpdate) {
        ResponseLocationDTO response = locationService.updateLocation(toUpdate);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Boolean> deleteLocation(@PathVariable("locationId") Long id) {
        Boolean response = locationService.deleteLocation(id);
        return ResponseEntity.ok(response);
    }
}