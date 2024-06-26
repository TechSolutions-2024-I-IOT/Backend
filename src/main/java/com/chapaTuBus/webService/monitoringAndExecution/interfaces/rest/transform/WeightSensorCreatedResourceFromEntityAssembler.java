package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.WeightSensor;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.WeightSensorCreatedResource;

public class WeightSensorCreatedResourceFromEntityAssembler {
    public static WeightSensorCreatedResource toResourceFromEntity (WeightSensor entity){
        return new WeightSensorCreatedResource(
                entity.getId(),
                (long) entity.getUnitBusId()
        );
    }
}
