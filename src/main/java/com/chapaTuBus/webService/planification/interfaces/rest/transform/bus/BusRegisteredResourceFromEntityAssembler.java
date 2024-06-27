package com.chapaTuBus.webService.planification.interfaces.rest.transform.bus;

import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.BusRegisteredResource;

public class BusRegisteredResourceFromEntityAssembler {

    public static BusRegisteredResource toResourceFromEntity(Bus entity){
        return new BusRegisteredResource(
                entity.getId(),
                entity.getLicensePlate(),
                entity.getSeatingCapacity(),
                entity.getTotalCapacity(),
                entity.getYear(),
                entity.getState(),
                entity.getUser(),
                entity.isDeleted()
        );
    }
}
