package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.gpsTracker;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.GpsTracker;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.gpsTracker.GpsTrackerCreatedResource;

public class GpsTrackerCreatedResourceFromEntityAssembler {
    public static GpsTrackerCreatedResource toResourceFromEntity(GpsTracker entity){
        return new GpsTrackerCreatedResource(
                entity.getId(),
                entity.getUnitBusId(),
        entity.getModel()
        );
    }
}
