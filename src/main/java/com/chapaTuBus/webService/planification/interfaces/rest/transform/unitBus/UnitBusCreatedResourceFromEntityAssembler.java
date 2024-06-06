package com.chapaTuBus.webService.planification.interfaces.rest.transform.unitBus;

import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.UnitBusCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.BusDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.DriverDto;

public class UnitBusCreatedResourceFromEntityAssembler {

    public static UnitBusCreatedResource toResourceFromEntity(UnitBus entity){
        return new UnitBusCreatedResource(
                entity.getId(),
                new DriverDto(entity.getDriver().getId(), entity.getDriver().getFirstName(), entity.getDriver().getLastName()),
                new BusDto(entity.getBus().getId(),entity.getBus().getLicensePlate()),
                entity.getUser()
        );
    }
}
