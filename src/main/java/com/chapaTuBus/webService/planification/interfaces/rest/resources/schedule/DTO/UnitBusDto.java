package com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.DTO;

import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.BusDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.DriverDto;

public record UnitBusDto(
        int unitBusId,
        DriverDto driver,
        BusDto bus
) {
}
