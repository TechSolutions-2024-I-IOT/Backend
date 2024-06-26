package com.chapaTuBus.webService.monitoringAndExecution.domain.services;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.WeightSensor;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.CreateWeightSensorCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.RegisterBusCapacityCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusCapacity;

import java.util.Optional;

public interface WeightSensorCommandService {
    Optional<WeightSensor> handle(CreateWeightSensorCommand command);
    Optional<BusCapacity> handle(RegisterBusCapacityCommand command);
}
