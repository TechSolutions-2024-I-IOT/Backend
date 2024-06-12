package com.chapaTuBus.webService.planification.interfaces.rest.transform.unitBus;

import com.chapaTuBus.webService.planification.domain.model.commands.bus.ModifyBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.unitBus.ModifyUnitBusCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.ModifyUnitBusResource;

public class ModifyUnitBusCommandFromResourceAssembler {
    public static ModifyUnitBusCommand toCommand(Long unitBusId, int userId, ModifyUnitBusResource resource){
        return new ModifyUnitBusCommand(
                unitBusId,
                userId,
                resource.driverId,
                resource.busId
        );
    }
}
