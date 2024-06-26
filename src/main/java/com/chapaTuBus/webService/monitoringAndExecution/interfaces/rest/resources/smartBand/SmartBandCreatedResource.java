package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.smartBand;

public record SmartBandCreatedResource(
        Long id,
        Long driverId,
        String model
) {
}
