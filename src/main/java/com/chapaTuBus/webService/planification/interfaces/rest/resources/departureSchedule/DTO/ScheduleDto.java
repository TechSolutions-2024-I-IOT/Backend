package com.chapaTuBus.webService.planification.interfaces.rest.resources.departureSchedule.DTO;

import java.time.LocalDate;

public record ScheduleDto(
        Long id,
        String date,
        String description
)
{
}
