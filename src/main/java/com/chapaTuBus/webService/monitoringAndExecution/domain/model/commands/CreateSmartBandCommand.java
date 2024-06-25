package com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands;

public record CreateSmartBandCommand (
        Long driverId,
        String model
){
}
