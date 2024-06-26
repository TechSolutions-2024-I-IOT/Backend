package com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.transform;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusCapacity;
import com.chapaTuBus.webService.monitoringAndExecution.interfaces.rest.resources.BusCapacityCreatedResource;

import java.time.format.DateTimeFormatter;

public class BusCapacityCreatedResourceFromEntityAssembler {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        public static BusCapacityCreatedResource toResourceFromEntity(BusCapacity entity) {
            String formattedTimeStamp = entity.getTimeStamp().format(formatter);
            return new BusCapacityCreatedResource(
                    entity.getId(),
                    entity.getWeightSensor().getId(),
                    entity.getBusCapacity(),
                    formattedTimeStamp
            );
        }
}
