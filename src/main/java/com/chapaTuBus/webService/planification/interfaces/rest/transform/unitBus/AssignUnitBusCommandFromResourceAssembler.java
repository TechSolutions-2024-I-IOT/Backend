package com.chapaTuBus.webService.planification.interfaces.rest.transform.unitBus;

import com.chapaTuBus.webService.planification.domain.model.commands.unitBus.AssignUnitBusCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.AssignUnitBusResource;

public class AssignUnitBusCommandFromResourceAssembler {
    public static AssignUnitBusCommand toCommand(AssignUnitBusResource assignUnitBusResource){
        return new AssignUnitBusCommand(
                assignUnitBusResource.transportCompanyId(),
                Driver.builder().id(assignUnitBusResource.driverId()).build(),
                Bus.builder().id(assignUnitBusResource.busId()).build()
        );
    }
}
