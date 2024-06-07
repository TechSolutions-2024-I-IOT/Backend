package com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.DTO;

import java.time.LocalTime;

public record DepartureScheduleDto(
        Long id,
        String departureTime,
        int roundNumber,
        UnitBusDto unitBus,
        boolean isDeleted
) {
}
