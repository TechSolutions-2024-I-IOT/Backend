package com.chapaTuBus.webService.planification.domain.model.commands.schedule;

import java.time.LocalTime;
import java.util.List;

public record DepartureScheduleCommand(
        List<LocalTime> times,
        int roundNumber,
        Long unitBusId,
        int userId
){}
