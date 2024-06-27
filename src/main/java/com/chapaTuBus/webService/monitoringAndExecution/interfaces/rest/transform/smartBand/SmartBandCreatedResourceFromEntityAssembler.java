package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.smartBand;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.SmartBand;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.smartBand.SmartBandCreatedResource;

public class SmartBandCreatedResourceFromEntityAssembler {
    public static SmartBandCreatedResource toResourceFromEntity (SmartBand entity){
        return new SmartBandCreatedResource(
                entity.getId(),
                (long) entity.getDriverId(),
                entity.getModel()
        );
    }
}
