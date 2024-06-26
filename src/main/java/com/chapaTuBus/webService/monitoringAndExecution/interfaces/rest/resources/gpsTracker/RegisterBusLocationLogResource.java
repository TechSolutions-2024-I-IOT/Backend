package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.gpsTracker;

public record RegisterBusLocationLogResource(
        int GpsTrackerId,
        String latitude,
        String longitude,
        String speed
) {
}
