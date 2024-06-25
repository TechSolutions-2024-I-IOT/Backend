package com.chapaTuBus.webService.monitoringAndExecution.domain.services;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.HeartRateLog;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.queries.GetAllHeartRateLogsBySmartBandIdQuery;

import java.util.List;

public interface SmartBandQueryService {
    List<HeartRateLog> handle(GetAllHeartRateLogsBySmartBandIdQuery query);
}
