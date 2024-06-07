package com.chapaTuBus.webService.iot.GPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("location-data")
    public ResponseEntity<List<Location>> getAllLocationData() {
        List<Location> locations = (List<Location>) locationRepository.findAll();
        return ResponseEntity.ok(locations);
    }

    @PostMapping("location-data")
    public ResponseEntity<Location> createLocationData(@RequestBody Location location) {
        Location savedLocation = locationRepository.save(location);
        return ResponseEntity.ok(savedLocation);
    }
}