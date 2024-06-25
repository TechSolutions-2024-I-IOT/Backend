package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources;

public record HeartRateLogCreatedResource(
        Long id,
        Long smartBandId,
        int heartRate,
        String timeStamp
) {
}
