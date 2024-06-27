package com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus;

import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.BusDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.DriverDto;

public record UnitBusInformationResource(Long id,
                                         DriverDto driver,
                                         BusDto bus,
                                         int user,
                                         Integer weightSensorId,
                                         boolean isDeleted
) {
}