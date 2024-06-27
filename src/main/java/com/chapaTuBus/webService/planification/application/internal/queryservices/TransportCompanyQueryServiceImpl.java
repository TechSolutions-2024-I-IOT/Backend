package com.chapaTuBus.webService.planification.application.internal.queryservices;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.domain.model.entities.*;
import com.chapaTuBus.webService.planification.domain.model.queries.*;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyQueryService;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.TransportCompanyRepository;
import org.springframework.stereotype.Service;

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

    @Override
    public List<TransportCompany> handle(GetAllTransportCompaniesQuery query) {
        return transportCompanyRepository.findAll();
    }
    @Override
    public Optional<TransportCompany> handle(GetTransportCompanyByUserIdQuery query){
        return transportCompanyRepository.findByUserId(query.userId());
    }
    @Override
    public Optional<Driver> handle(GetDriverByIdQuery query) {
        return transportCompanyRepository.findDriverById(query.driverId());
    }

    @Override
    public List<Schedule> handle(GetAllSchedulesByUserIdQuery query) {
        return transportCompanyRepository.findSchedulesByUserId(query.userId());
    }

    @Override
    public Optional<TransportCompany> handle(GetTransportCompanyInformationByIdQuery query) {
        return transportCompanyRepository.findById(query.transportCompanyId());
    }

    @Override
    public Optional<Schedule> handle(GetScheduleCompleteInformationByScheduleIdQuery query) {
        return transportCompanyRepository.findScheduleById(query.ScheduleId());
    }

    @Override
    public Optional<Itinerary> handle(GetItineraryByUserIdQuery query) {
        return transportCompanyRepository.findItineraryByUserId(query.userId());
    }
}