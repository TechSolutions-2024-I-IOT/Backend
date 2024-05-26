package com.chapaTuBus.webService.planification.domain.services;

import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllBusesByTransportCompanyIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllDriversByTransportCompanyIdQuery;

import java.util.List;

public interface TransportCompanyQueryService {
    List<Driver> handle(GetAllDriversByTransportCompanyIdQuery query);
    List<Bus> handle(GetAllBusesByTransportCompanyIdQuery query);
}
