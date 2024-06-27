package com.chapaTuBus.webService.planification.interfaces.rest.transform.unitBus;

import com.chapaTuBus.webService.planification.domain.model.commands.bus.ModifyBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.unitBus.ModifyUnitBusCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.ModifyUnitBusResource;

public class ModifyUnitBusCommandFromResourceAssembler {
    public static ModifyUnitBusCommand toCommand( int userId, Long unitBusId, ModifyUnitBusResource resource){
        return new ModifyUnitBusCommand(
                userId,
                unitBusId,
                resource.driverId(),
                resource.busId()
        );
    }
}
