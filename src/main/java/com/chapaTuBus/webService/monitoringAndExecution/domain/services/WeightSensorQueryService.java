package com.chapaTuBus.webService.monitoringAndExecution.domain.services;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusCapacity;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.queries.GetBusCapacityByWeightSensorIdQuery;

import java.util.List;
public interface WeightSensorQueryService {
    List<BusCapacity> handle(GetBusCapacityByWeightSensorIdQuery query);
}
