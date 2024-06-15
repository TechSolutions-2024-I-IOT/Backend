package com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.DTO;

import java.util.List;

public record DepartureScheduleWithUnitBusInformationDto  (
                List<String> times,
                 int roundNumber,
                 UnitBusDto unitBus
)
{
}
