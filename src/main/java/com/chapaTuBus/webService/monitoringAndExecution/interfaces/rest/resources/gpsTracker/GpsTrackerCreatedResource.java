package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.gpsTracker;

public record GpsTrackerCreatedResource(
        Long id,
        Long unitBusId,
        String model
) {
}
