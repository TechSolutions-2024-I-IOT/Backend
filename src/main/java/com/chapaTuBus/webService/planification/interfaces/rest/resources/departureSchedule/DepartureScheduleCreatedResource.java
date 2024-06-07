package com.chapaTuBus.webService.planification.interfaces.rest.resources.departureSchedule;

import com.chapaTuBus.webService.planification.interfaces.rest.resources.departureSchedule.DTO.ScheduleDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.UnitBusDto;

public record DepartureScheduleCreatedResource(
        Long id,
        int roundNumber,
        UnitBusDto unitBusDto,
        ScheduleDto scheduleDto,
        int user,
        boolean isDeleted
) {
}
