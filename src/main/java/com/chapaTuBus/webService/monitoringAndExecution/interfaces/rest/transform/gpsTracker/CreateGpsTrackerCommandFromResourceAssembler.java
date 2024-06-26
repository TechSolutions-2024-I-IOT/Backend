package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.gpsTracker;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.CreateGPSTrackerCommand;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.gpsTracker.CreateGpsTrackerResource;

public class CreateGpsTrackerCommandFromResourceAssembler {
    public static CreateGPSTrackerCommand toCommand (CreateGpsTrackerResource resource){
        return new CreateGPSTrackerCommand(
                resource.unitBusId(),
                resource.model()
        );
    }
}
