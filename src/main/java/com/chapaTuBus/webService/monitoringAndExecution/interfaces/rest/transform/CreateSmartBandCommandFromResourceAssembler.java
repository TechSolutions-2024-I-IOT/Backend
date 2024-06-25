package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.CreateSmartBandCommand;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.CreateSmartBandResource;

public class CreateSmartBandCommandFromResourceAssembler {
    public static CreateSmartBandCommand toCommand (CreateSmartBandResource resource){
        return new CreateSmartBandCommand(
                resource.driverId(),
                resource.model()
        );
    }
}
