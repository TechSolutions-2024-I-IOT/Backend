package com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule;

import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.DTO.DepartureScheduleWithUnitBusInformationDto;

import java.util.List;

public record ScheduleWithDepartureSchedulesCompleteInformationResource(
        Long id,
        String date,
        String description,
        List<DepartureScheduleWithUnitBusInformationDto> departureSchedules,
        int user,
        boolean isDeleted
) {
}
