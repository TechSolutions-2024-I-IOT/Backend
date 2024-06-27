package com.chapaTuBus.webService.planification.interfaces.rest.transform.unitBus;

import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.UnitBusCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.UnitBusInformationResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.BusDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.DriverDto;

public class UnitBusInformationResourceFromEntityAssembler
{
    public static UnitBusInformationResource toResourceFromEntity(UnitBus entity) {
        Integer weightSensorId = null;
        if (entity.getWeightSensor() != null) {
            weightSensorId = Math.toIntExact(entity.getWeightSensor().getId());
        }

        return new UnitBusInformationResource(
                entity.getId(),
                new DriverDto(entity.getDriver().getId(), entity.getDriver().getFirstName(), entity.getDriver().getLastName()),
                new BusDto(entity.getBus().getId(), entity.getBus().getLicensePlate()),
                entity.getUser(),
                weightSensorId, // Esto puede ser null ahora
                entity.isDeleted()
        );
    }
}
