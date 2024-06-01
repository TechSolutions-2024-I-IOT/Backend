package com.chapaTuBus.webService.planification.domain.services;

import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.domain.model.entities.DepartureSchedule;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllBusesByUserIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllDepartureSchedulesByUserIdAndScheduleIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllDriversByUserIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllUnitBusesByUserIdQuery;

import java.util.List;

public interface TransportCompanyQueryService {
    List<Driver> handle(GetAllDriversByUserIdQuery query);
    List<Bus> handle(GetAllBusesByUserIdQuery query);
    List<UnitBus>handle(GetAllUnitBusesByUserIdQuery query);
    List<DepartureSchedule>handle(GetAllDepartureSchedulesByUserIdAndScheduleIdQuery query);
}
