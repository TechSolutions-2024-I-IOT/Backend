package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.CreateWeightSensorCommand;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.CreateWeightSensorResource;

public class CreateWeightSensorCommandFromResourceAssembler {
    public static CreateWeightSensorCommand toCommand (CreateWeightSensorResource resource){
        return new CreateWeightSensorCommand(
                resource.unitBusId()
        );
    }
}
