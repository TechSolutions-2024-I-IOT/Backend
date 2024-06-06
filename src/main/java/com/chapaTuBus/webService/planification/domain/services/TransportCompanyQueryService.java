package com.chapaTuBus.webService.planification.domain.services;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.domain.model.entities.DepartureSchedule;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import com.chapaTuBus.webService.planification.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface TransportCompanyQueryService {
    List<Driver> handle(GetAllDriversByUserIdQuery query);
    List<Bus> handle(GetAllBusesByUserIdQuery query);
    List<UnitBus>handle(GetAllUnitBusesByUserIdQuery query);
    List<DepartureSchedule>handle(GetAllDepartureSchedulesByUserIdAndScheduleIdQuery query);
    List<TransportCompany>handle(GetAllTransportCompaniesQuery query);
    Optional<Driver> handle(GetDriverByIdQuery query);
}
