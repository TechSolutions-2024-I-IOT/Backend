package com.chapaTuBus.webService.planification.application.internal.queryservices;

import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.domain.model.entities.DepartureSchedule;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllBusesByUserIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllDepartureSchedulesByUserIdAndScheduleIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllDriversByUserIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllUnitBusesByUserIdQuery;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyQueryService;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.TransportCompanyRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service

public class TransportCompanyQueryServiceImpl implements TransportCompanyQueryService {


    private final TransportCompanyRepository transportCompanyRepository;


    public TransportCompanyQueryServiceImpl(
            TransportCompanyRepository transportCompanyRepository) {
        this.transportCompanyRepository = transportCompanyRepository;
    }

    @Override
    public List<Driver> handle(GetAllDriversByUserIdQuery query) {

        return transportCompanyRepository.findDriversByUserId(query.id());

    }

    @Override
    public List<Bus> handle(GetAllBusesByUserIdQuery query) {

        return transportCompanyRepository.findBusesByUserId(query.id());
    }

    @Override
    public List<UnitBus> handle(GetAllUnitBusesByUserIdQuery query) {
       // return unitBusRepository.findAllByUser(query.id());
        return transportCompanyRepository.findUnitBusesByUserId(query.id());
    }

    @Override
    public List<DepartureSchedule> handle(GetAllDepartureSchedulesByUserIdAndScheduleIdQuery query){
        return transportCompanyRepository.findAllDepartureSchedulesByUserIdAndScheduleId(query.userId(), query.scheduleId());
    }
}
