package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.gpsTracker;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.RegisterBusLocationLogCommand;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.gpsTracker.RegisterBusLocationLogResource;

public class RegisterBusLocationLogCommandFromResourceAssembler {
    public static RegisterBusLocationLogCommand toCommand(RegisterBusLocationLogResource resource){
        return new RegisterBusLocationLogCommand(
                (long) resource.GpsTrackerId(),
                resource.latitude(),
                resource.longitude(),
                resource.speed()
        );
    }
}
