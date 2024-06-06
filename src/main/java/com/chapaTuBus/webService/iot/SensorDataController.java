package com.chapaTuBus.webService.iot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/")
public class SensorDataController {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @GetMapping("sensor-data")
    public ResponseEntity<List<SensorData>> getAllSensorData() {
        List<SensorData> sensorDatas = (List<SensorData>) sensorDataRepository.findAll();
        return ResponseEntity.ok(sensorDatas);
    }

    @PostMapping("sensor-data")
    public ResponseEntity<SensorData> createSensorData(@RequestBody SensorData sensorData) {
        SensorData savedSensorData = sensorDataRepository.save(sensorData);
        return ResponseEntity.ok(savedSensorData);
    }
}