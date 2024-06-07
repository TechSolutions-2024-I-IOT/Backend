package com.chapaTuBus.webService.planification.interfaces.rest.transform.bus;

import com.chapaTuBus.webService.planification.domain.model.commands.bus.DeleteBusCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.SoftDeleteBusResource;

public class DeleteBusCommandFromResourceAssembler {
    public static DeleteBusCommand toCommand(SoftDeleteBusResource resource){
        return new DeleteBusCommand(resource.busId());
    }
}
