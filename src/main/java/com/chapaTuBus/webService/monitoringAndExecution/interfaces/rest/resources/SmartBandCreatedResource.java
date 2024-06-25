package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources;

public record SmartBandCreatedResource(
        Long id,
        Long driverId,
        String model
) {
}
