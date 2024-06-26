package com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands;

public record RegisterBusCapacityCommand(
        Long weightSensorId,
        int busCapacity
) {
}
