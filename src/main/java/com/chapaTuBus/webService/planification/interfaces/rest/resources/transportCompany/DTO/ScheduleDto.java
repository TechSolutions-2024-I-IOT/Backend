package com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.DTO;

import java.util.List;

public record ScheduleDto(
        Long id,
        String date,
        String description,
        List<DepartureScheduleDto> departureSchedules,
        boolean isDeleted
) {
}
