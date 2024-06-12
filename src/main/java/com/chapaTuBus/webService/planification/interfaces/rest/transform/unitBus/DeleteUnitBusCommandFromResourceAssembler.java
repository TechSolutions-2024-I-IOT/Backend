package com.chapaTuBus.webService.planification.interfaces.rest.transform.unitBus;

import com.chapaTuBus.webService.planification.domain.model.commands.bus.DeleteBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.unitBus.DeleteUnitBusCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.SoftDeleteBusResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.SoftDeleteUnitBusResource;

public class DeleteUnitBusCommandFromResourceAssembler {
    public static DeleteUnitBusCommand toCommand(SoftDeleteUnitBusResource resource){
        return new DeleteUnitBusCommand(resource.unitBusId());
    }
}
