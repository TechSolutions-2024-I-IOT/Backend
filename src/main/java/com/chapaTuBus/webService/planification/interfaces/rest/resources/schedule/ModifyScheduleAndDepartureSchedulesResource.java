package com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule;

import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.DTO.DepartureScheduleDto;

import java.util.List;

public record ModifyScheduleAndDepartureSchedulesResource(
        String date,
        String description,
        List<DepartureScheduleDto> departureScheduleDto,
        int userId
) {
}
