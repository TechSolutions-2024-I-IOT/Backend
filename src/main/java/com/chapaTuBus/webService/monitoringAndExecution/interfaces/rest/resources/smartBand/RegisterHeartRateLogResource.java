package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.smartBand;

public record RegisterHeartRateLogResource(
        int SmartBandId,
        int heartRate
) {
}
