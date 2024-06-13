package com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus;

import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.BusDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.DriverDto;

public record ModifiedUnitBusResource (
        Long id,
        DriverDto driver,
        BusDto bus,
        int user,
        boolean isDeleted
){
}
