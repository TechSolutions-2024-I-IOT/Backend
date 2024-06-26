package com.chapaTuBus.webService.monitoringAndExecution.application.internal.commandhandlers;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.GpsTracker;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.CreateGPSTrackerCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.RegisterBusLocationLogCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusLocationLog;
import com.chapaTuBus.webService.monitoringAndExecution.domain.services.GpsTrackerCommandService;
import com.chapaTuBus.webService.monitoringAndExecution.infraestructure.repositories.jpa.GpsTrackerRepository;
import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.TransportCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GpsTrackerCommandServiceImpl implements GpsTrackerCommandService {
    private final GpsTrackerRepository gpsTrackerRepository;
    private final TransportCompanyRepository transportCompanyRepository;

    public GpsTrackerCommandServiceImpl(GpsTrackerRepository gpsTrackerRepository, TransportCompanyRepository transportCompanyRepository) {
        this.gpsTrackerRepository = gpsTrackerRepository;
        this.transportCompanyRepository = transportCompanyRepository;
    }

    @Override
    public Optional<GpsTracker> handle(CreateGPSTrackerCommand command) {
        Optional<UnitBus> unitBusOptional = transportCompanyRepository.findUnitBusById(Math.toIntExact(command.unitBusId()));
        if (unitBusOptional.isEmpty()) return Optional.empty();

        UnitBus unitBus = unitBusOptional.get();
        if(unitBus.getGpsTracker() != null) return Optional.empty(); // Verificar si ya tiene un GpsTracker asignado

        GpsTracker gpsTracker = new GpsTracker(command);
        gpsTrackerRepository.save(gpsTracker);

        unitBus.setGpsTracker(gpsTracker);
        gpsTracker.setUnitBusId(unitBus.getId());

        transportCompanyRepository.save(unitBus.getTransportCompany());
        return Optional.of(gpsTracker);
    }

    @Override
    public Optional<BusLocationLog> handle(RegisterBusLocationLogCommand command) {

        Optional<GpsTracker> gpsTrackerOpt = gpsTrackerRepository.findById(command.gpsTrackerId());
        if(gpsTrackerOpt.isEmpty()) return Optional.empty();

        GpsTracker gpsTracker = gpsTrackerOpt.get();

        BusLocationLog busLocationLog = gpsTracker.registerNewBusLocationLog(command);
        gpsTrackerRepository.save(gpsTracker);

        BusLocationLog savedBusLocationLog = gpsTracker.getBusLocationLogs().stream()
                .filter(log -> log.getTimeStamp().equals(busLocationLog.getTimeStamp()))
                .findFirst()
                .orElse(busLocationLog);
        return Optional.of(savedBusLocationLog);
    }
}
