package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources;

public record BusCapacityCreatedResource(
        Long id,
        Long weightSensorId,
        int busCapacity,
        String timeStamp
) {
}
