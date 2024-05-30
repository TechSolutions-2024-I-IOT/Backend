package com.chapaTuBus.webService.planification.domain.services;

import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllBusesByTransportCompanyIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllDriversByUserIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllUnitBusesByTransportCompanyIdQuery;

import java.util.List;

public interface TransportCompanyQueryService {
    List<Driver> handle(GetAllDriversByUserIdQuery query);
    List<Bus> handle(GetAllBusesByTransportCompanyIdQuery query);
    List<UnitBus>handle(GetAllUnitBusesByTransportCompanyIdQuery query);
}
