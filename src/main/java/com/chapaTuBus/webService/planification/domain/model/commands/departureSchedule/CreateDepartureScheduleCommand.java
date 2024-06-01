package com.chapaTuBus.webService.planification.domain.model.commands.departureSchedule;

import java.time.LocalTime;

public record CreateDepartureScheduleCommand
        (LocalTime time,
         int roundNumber,
         int unitBusId,
         int scheduleId,
         int user
         )
{
}
