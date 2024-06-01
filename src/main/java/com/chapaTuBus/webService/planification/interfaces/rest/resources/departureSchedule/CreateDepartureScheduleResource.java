package com.chapaTuBus.webService.planification.interfaces.rest.resources.departureSchedule;

import java.time.LocalTime;

public record CreateDepartureScheduleResource(
        String time,
        int roundNumber  ,
        int unitBusId,
        int scheduleId,
        int user
)
{
}
