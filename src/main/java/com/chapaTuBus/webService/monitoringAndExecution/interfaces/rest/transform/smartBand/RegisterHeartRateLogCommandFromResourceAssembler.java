package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.smartBand;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.RegisterHeartRateLogCommand;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.smartBand.RegisterHeartRateLogResource;

public class RegisterHeartRateLogCommandFromResourceAssembler {

    public static RegisterHeartRateLogCommand toCommand(RegisterHeartRateLogResource resource){
        return new RegisterHeartRateLogCommand(
                (long) resource.SmartBandId(),
                resource.heartRate()
        );
    }
}
