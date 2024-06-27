package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.gpsTracker;

public record BusLocationLogCreatedResource (
        Long id,
        Long GpsTrackerId,
        String latitude,
        String longitude,
        String speed,
        String timeStamp
){
}
