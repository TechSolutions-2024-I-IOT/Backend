package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform.smartBand;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.HeartRateLog;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.smartBand.HeartRateLogCreatedResource;

import java.time.format.DateTimeFormatter;

public class HeartRateLogCreatedResourceFromEntityAssembler {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static HeartRateLogCreatedResource toResourceFromEntity(HeartRateLog entity) {
        String formattedTimeStamp = entity.getTimeStamp().format(formatter);
        return new HeartRateLogCreatedResource(
                entity.getId(),
                entity.getSmartBand().getId(),
                entity.getHeartRate(),
                formattedTimeStamp
        );
    }
}
