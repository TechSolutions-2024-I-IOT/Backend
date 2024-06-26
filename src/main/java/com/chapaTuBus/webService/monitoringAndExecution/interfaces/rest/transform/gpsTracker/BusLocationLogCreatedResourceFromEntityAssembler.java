package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.gpsTracker;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusLocationLog;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.gpsTracker.BusLocationLogCreatedResource;

import java.time.format.DateTimeFormatter;

public class BusLocationLogCreatedResourceFromEntityAssembler {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static BusLocationLogCreatedResource toResourceFromEntity(BusLocationLog entity){
        String formattedTimeStamp = entity.getTimeStamp().format(formatter);
        return new BusLocationLogCreatedResource(
                entity.getId(),
                entity.getGpsTracker().getId(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getSpeed(),
                formattedTimeStamp
        );
    }
}
