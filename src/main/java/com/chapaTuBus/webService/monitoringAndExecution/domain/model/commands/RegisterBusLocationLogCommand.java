package com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands;

public record RegisterBusLocationLogCommand(
        Long gpsTrackerId,
        String latitude,
        String longitude,
        String speed
){
}
