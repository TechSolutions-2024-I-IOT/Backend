package com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.DTO;

import com.chapaTuBus.webService.planification.domain.model.entities.DepartureTime;

import java.time.LocalTime;
import java.util.List;

public record DepartureScheduleDto(
        Long id,
        List<DepartureTime> times,
        int roundNumber,
        UnitBusDto unitBus,
        boolean isDeleted
) {
}
