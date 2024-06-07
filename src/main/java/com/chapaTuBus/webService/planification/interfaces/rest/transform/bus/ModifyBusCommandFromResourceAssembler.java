package com.chapaTuBus.webService.planification.interfaces.rest.transform.bus;

import com.chapaTuBus.webService.planification.domain.model.commands.bus.ModifyBusCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.ModifyBusResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.ModifyDriverResource;

public class ModifyBusCommandFromResourceAssembler {

    public static ModifyBusCommand toCommand(Long busId, int userId, ModifyBusResource resource){
        return new ModifyBusCommand(
                busId,
                userId,
                resource.licensePlate(),
                resource.seatingCapacity(),
                resource.totalCapacity(),
                resource.year(),
                resource.state()
        );
    }
}
