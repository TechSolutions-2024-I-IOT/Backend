package com.chapaTuBus.webService.planification.domain.services;

import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllDriversByTransportCompanyIdQuery;

import java.util.List;

public interface TransportCompanyQueryService {
    List<Driver> handle(GetAllDriversByTransportCompanyIdQuery query);
}
