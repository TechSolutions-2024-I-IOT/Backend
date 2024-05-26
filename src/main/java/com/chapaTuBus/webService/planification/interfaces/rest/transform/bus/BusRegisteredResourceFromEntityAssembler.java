package com.chapaTuBus.webService.planification.interfaces.rest.transform.bus;

import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.BusRegisteredResoruce;

public class BusRegisteredResourceFromEntityAssembler {

    public static BusRegisteredResoruce toResourceFromEntity(Bus entity){
        return new BusRegisteredResoruce(
                entity.getId(),
                entity.getLicensePlate(),
                entity.getSeatingCapacity(),
                entity.getTotalCapacity(),
                entity.getYear(),
                entity.getState()
        );
    }
}
