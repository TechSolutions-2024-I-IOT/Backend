package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources;

public record RegisterHeartRateLogResource(
        int SmartBandId,
        int heartRate
) {
}
