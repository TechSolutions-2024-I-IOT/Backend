package com.chapaTuBus.webService.monitoringAndExecution.domain.services;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusLocationLog;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.queries.GetAllBusLocationLogsByGpsTrackerIdQuery;

import java.util.List;

public interface GpsTrackerQueryService {
    List<BusLocationLog> handle(GetAllBusLocationLogsByGpsTrackerIdQuery query);
}
