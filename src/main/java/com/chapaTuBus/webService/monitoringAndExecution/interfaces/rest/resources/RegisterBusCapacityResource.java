package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources;

public record RegisterBusCapacityResource(
        int weightSensorId,
        int busCapacity
) {
}
