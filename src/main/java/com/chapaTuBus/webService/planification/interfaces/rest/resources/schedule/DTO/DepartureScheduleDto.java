package com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.DTO;

import java.time.LocalTime;
import java.util.List;

public record DepartureScheduleDto(
        List<String> times,
        int roundNumber,
        int unitBusId
) {
}
