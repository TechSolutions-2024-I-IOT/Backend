package com.chapaTuBus.webService.monitoringAndExecution.domain.services;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.SmartBand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.CreateSmartBandCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.RegisterHeartRateLogCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.HeartRateLog;

import java.util.Optional;

public interface SmartBandCommandService {
    Optional<SmartBand> handle(CreateSmartBandCommand command);
    Optional<HeartRateLog> handle(RegisterHeartRateLogCommand command);
}
