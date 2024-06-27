package com.chapaTuBus.webService.monitoringAndExecution.domain.services;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.GpsTracker;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.CreateGPSTrackerCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.RegisterBusLocationLogCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusLocationLog;

import java.util.Optional;

public interface GpsTrackerCommandService {
    Optional<GpsTracker> handle(CreateGPSTrackerCommand command);
    Optional<BusLocationLog> handle(RegisterBusLocationLogCommand command);
}
