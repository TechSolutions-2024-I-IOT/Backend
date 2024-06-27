package com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands;

import java.time.LocalDateTime;

public record RegisterHeartRateLogCommand(
        Long smartBandId,
        int heartRate
) {
}
