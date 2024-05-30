package com.chapaTuBus.webService.planification.application.internal.queryservices;

import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllBusesByTransportCompanyIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllDriversByUserIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllUnitBusesByTransportCompanyIdQuery;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyQueryService;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.BusRepository;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.DriverRepository;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.TransportCompanyRepository;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.UnitBusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TransportCompanyQueryServiceImpl implements TransportCompanyQueryService {


    //private final TransportCompanyRepository transportCompanyRepository;
    private final DriverRepository driverRepository;
    private final BusRepository busRepository;

    private final UnitBusRepository unitBusRepository;

    public TransportCompanyQueryServiceImpl(
            TransportCompanyRepository transportCompanyRepository,
            DriverRepository driverRepository,
            BusRepository busRepository,
            UnitBusRepository unitBusRepository) {
        //this.transportCompanyRepository = transportCompanyRepository;
        this.driverRepository=driverRepository;
        this.busRepository=busRepository;
        this.unitBusRepository = unitBusRepository;
    }

    @Override
    public List<Driver> handle(GetAllDriversByUserIdQuery query) {
        return driverRepository.findAllByUser(query.id());
    }

    @Override
    public List<Bus> handle(GetAllBusesByTransportCompanyIdQuery query) {
        return busRepository.findAllByTransportCompanyId(query.id());
    }

    @Override
    public List<UnitBus> handle(GetAllUnitBusesByTransportCompanyIdQuery query) {
        return unitBusRepository.findAllByTransportCompanyId(query.id());
    }

}
