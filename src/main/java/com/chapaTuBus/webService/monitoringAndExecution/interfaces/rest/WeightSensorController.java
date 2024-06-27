package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest;


import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.WeightSensor;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusCapacity;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.queries.GetBusCapacityByWeightSensorIdQuery;
import com.chapaTuBus.webService.monitoringAndExecution.domain.services.WeightSensorCommandService;
import com.chapaTuBus.webService.monitoringAndExecution.domain.services.WeightSensorQueryService;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.CreateWeightSensorResource;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.BusCapacityCreatedResource;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.RegisterBusCapacityResource;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.WeightSensorCreatedResource;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.CreateWeightSensorCommandFromResourceAssembler;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.BusCapacityCreatedResourceFromEntityAssembler;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.RegisterBusCapacityCommandFromResourceAssembler;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.WeightSensorCreatedResourceFromEntityAssembler;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/weight-sensor")
public class WeightSensorController {

        private final WeightSensorCommandService weightSensorCommandService;
        private final WeightSensorQueryService weightSensorQueryService;

        public WeightSensorController(WeightSensorCommandService weightSensorCommandService,
                                      WeightSensorQueryService weightSensorQueryService) {
            this.weightSensorCommandService = weightSensorCommandService;
            this.weightSensorQueryService = weightSensorQueryService;
        }

        @GetMapping("/bus-capacities")
        ResponseEntity<List<BusCapacityCreatedResource>> getBusCapacities(@RequestParam(name = "weightSensorId") int weightSensorId){

            var getBusCapacityByWeightSensorId= new GetBusCapacityByWeightSensorIdQuery(weightSensorId);

            var busCapacities= weightSensorQueryService.handle(getBusCapacityByWeightSensorId);

            var busCapacitiesResources= busCapacities.stream()
                    .map(BusCapacityCreatedResourceFromEntityAssembler::toResourceFromEntity)
                    .toList();
            return ResponseEntity.ok(busCapacitiesResources);
        }

        @PostMapping("/new-weight-sensor")
        ResponseEntity<WeightSensorCreatedResource> createWeightSensor(@RequestBody CreateWeightSensorResource resource){

            Optional<WeightSensor> weightSensorOptional= weightSensorCommandService
                    .handle(
                            CreateWeightSensorCommandFromResourceAssembler.toCommand(resource)
                    );

            return weightSensorOptional.map(actualWeightSensor->
                    new ResponseEntity<>(WeightSensorCreatedResourceFromEntityAssembler
                            .toResourceFromEntity(actualWeightSensor),CREATED))
                    .orElseGet(()->ResponseEntity.badRequest().build());
        }

        @PostMapping("/register-bus-capacity")
        ResponseEntity<BusCapacityCreatedResource> registerBusCapacity(@RequestBody RegisterBusCapacityResource resource){

            Optional<BusCapacity> busCapacityOptional= weightSensorCommandService
                    .handle(
                            RegisterBusCapacityCommandFromResourceAssembler.toCommand(resource)
                    );

            return busCapacityOptional.map(actualBusCapacity->
                    new ResponseEntity<>(BusCapacityCreatedResourceFromEntityAssembler
                            .toResourceFromEntity(actualBusCapacity),CREATED))
                    .orElseGet(()->ResponseEntity.badRequest().build());
        }
}
