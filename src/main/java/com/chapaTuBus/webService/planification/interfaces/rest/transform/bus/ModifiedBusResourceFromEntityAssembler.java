package com.chapaTuBus.webService.planification.interfaces.rest.transform.bus;

import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.ModifiedBusResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.ModifiedDriverResource;

public class ModifiedBusResourceFromEntityAssembler {
    public static ModifiedBusResource toResourceFromEntity(Bus entity){
        return new ModifiedBusResource(
                entity.getId(),
                entity.getLicensePlate(),
                entity.getSeatingCapacity(),
                entity.getTotalCapacity(),
                entity.getYear(),
                entity.getState().name()
        );
    }
}
