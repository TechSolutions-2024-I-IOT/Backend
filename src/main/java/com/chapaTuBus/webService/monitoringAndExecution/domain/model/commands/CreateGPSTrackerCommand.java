package com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands;

public record CreateGPSTrackerCommand(
        Long unitBusId,
        String model
) {
}
