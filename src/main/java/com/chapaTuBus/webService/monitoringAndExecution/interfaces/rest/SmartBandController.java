package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest;


import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.SmartBand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.HeartRateLog;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.queries.GetAllHeartRateLogsBySmartBandIdQuery;
import com.chapaTuBus.webService.monitoringAndExecution.domain.services.SmartBandCommandService;
import com.chapaTuBus.webService.monitoringAndExecution.domain.services.SmartBandQueryService;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.smartBand.CreateSmartBandResource;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.smartBand.HeartRateLogCreatedResource;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.smartBand.RegisterHeartRateLogResource;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.smartBand.SmartBandCreatedResource;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.smartBand.CreateSmartBandCommandFromResourceAssembler;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.smartBand.HeartRateLogCreatedResourceFromEntityAssembler;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.smartBand.RegisterHeartRateLogCommandFromResourceAssembler;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.smartBand.SmartBandCreatedResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/smart-band")
public class SmartBandController {

    private final SmartBandCommandService smartBandCommandService;
    private final SmartBandQueryService smartBandQueryService;

    public SmartBandController(SmartBandCommandService smartBandCommandService, SmartBandQueryService smartBandQueryService) {
        this.smartBandCommandService = smartBandCommandService;
        this.smartBandQueryService = smartBandQueryService;
    }

    @GetMapping("/heart-rate-logs")
    ResponseEntity<List<HeartRateLogCreatedResource>> getHeartRateLogs(@RequestParam(name = "smartBandId") int smartBandId){

        var getAllHeartRateLogsBySmartBandId= new GetAllHeartRateLogsBySmartBandIdQuery(smartBandId);

        var heartRateLogs= smartBandQueryService.handle(getAllHeartRateLogsBySmartBandId);

        var heartRateLogsResources= heartRateLogs.stream()
                .map(HeartRateLogCreatedResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(heartRateLogsResources);
    }

    @PostMapping("/new-smart-band")
    ResponseEntity<SmartBandCreatedResource> createSmartBand(@RequestBody CreateSmartBandResource resource){

        Optional<SmartBand> smartBandOptional= smartBandCommandService
                .handle(
                        CreateSmartBandCommandFromResourceAssembler.toCommand(resource)
                );

        return smartBandOptional.map(actualSmartBand->
                new ResponseEntity<>(SmartBandCreatedResourceFromEntityAssembler.toResourceFromEntity(actualSmartBand),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PostMapping("/register-heart-rate-log")
    ResponseEntity<HeartRateLogCreatedResource> registerNewHeartRateLog(@RequestBody RegisterHeartRateLogResource resource){

        Optional<HeartRateLog> heartRateLog= smartBandCommandService
                .handle(
                        RegisterHeartRateLogCommandFromResourceAssembler.toCommand(resource)
                );

        return heartRateLog.map(actualHeartRate->
                new ResponseEntity<>(HeartRateLogCreatedResourceFromEntityAssembler.toResourceFromEntity(actualHeartRate),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }
}
