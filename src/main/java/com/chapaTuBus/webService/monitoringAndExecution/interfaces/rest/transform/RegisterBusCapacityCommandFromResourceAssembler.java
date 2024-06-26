package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.RegisterBusCapacityCommand;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.RegisterBusCapacityResource;

public class RegisterBusCapacityCommandFromResourceAssembler {

        public static RegisterBusCapacityCommand toCommand(RegisterBusCapacityResource resource){
            return new RegisterBusCapacityCommand(
                    (long) resource.weightSensorId(),
                    resource.busCapacity()
            );
        }
}
