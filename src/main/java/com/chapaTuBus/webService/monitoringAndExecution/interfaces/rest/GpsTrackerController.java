package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.GpsTracker;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.CreateGPSTrackerCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.RegisterBusLocationLogCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusLocationLog;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.queries.GetAllBusLocationLogsByGpsTrackerIdQuery;
import com.chapaTuBus.webService.monitoringAndExecution.domain.services.GpsTrackerCommandService;
import com.chapaTuBus.webService.monitoringAndExecution.domain.services.GpsTrackerQueryService;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.gpsTracker.BusLocationLogCreatedResource;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.gpsTracker.CreateGpsTrackerResource;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.gpsTracker.GpsTrackerCreatedResource;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.gpsTracker.RegisterBusLocationLogResource;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.gpsTracker.BusLocationLogCreatedResourceFromEntityAssembler;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.gpsTracker.CreateGpsTrackerCommandFromResourceAssembler;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.gpsTracker.GpsTrackerCreatedResourceFromEntityAssembler;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.gpsTracker.RegisterBusLocationLogCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/gps-tracker")
public class GpsTrackerController {
    private final GpsTrackerCommandService gpsTrackerCommandService;
    private final GpsTrackerQueryService gpsTrackerQueryService;

    public GpsTrackerController(GpsTrackerCommandService gpsTrackerCommandService, GpsTrackerQueryService gpsTrackerQueryService) {
        this.gpsTrackerCommandService = gpsTrackerCommandService;
        this.gpsTrackerQueryService = gpsTrackerQueryService;
    }

    @GetMapping("/bus-location-logs")
    ResponseEntity<List<BusLocationLogCreatedResource>> getBusLocationLogs(@RequestParam(name = "gpsLocationId") int gpsLocationId){
        var getAllBusLocationLogsByGpsTrackerId = new GetAllBusLocationLogsByGpsTrackerIdQuery(gpsLocationId);
        var busLocationLogs = gpsTrackerQueryService.handle(getAllBusLocationLogsByGpsTrackerId);
        var busLocationLogsResources = busLocationLogs.stream()
                .map(BusLocationLogCreatedResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(busLocationLogsResources);
    }

    @PostMapping("/new-gps-tracker")
    ResponseEntity<GpsTrackerCreatedResource> createdGpsTracker(@RequestBody CreateGpsTrackerResource resource){
        Optional<GpsTracker> gpsTrackerOptional = gpsTrackerCommandService
                .handle(CreateGpsTrackerCommandFromResourceAssembler.toCommand(resource));
        return gpsTrackerOptional.map(actualGpsTracker->
                new ResponseEntity<>(GpsTrackerCreatedResourceFromEntityAssembler.toResourceFromEntity(actualGpsTracker), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/register-bus-location-log")
    ResponseEntity<BusLocationLogCreatedResource> registerNewBusLocationLog(@RequestBody RegisterBusLocationLogResource resource){
        Optional<BusLocationLog> busLocationLog = gpsTrackerCommandService
                .handle(RegisterBusLocationLogCommandFromResourceAssembler.toCommand(resource));
        return busLocationLog.map(actualBusLocation ->
                new ResponseEntity<>(BusLocationLogCreatedResourceFromEntityAssembler.toResourceFromEntity(actualBusLocation), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
