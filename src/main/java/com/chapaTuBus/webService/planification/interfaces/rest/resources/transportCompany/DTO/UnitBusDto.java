package com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.DTO;

import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.BusDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.DriverDto;

public record UnitBusDto(
        Long id,
        DriverForUnitBusDto driver,
        BusForUnitBusDto bus
){
}
