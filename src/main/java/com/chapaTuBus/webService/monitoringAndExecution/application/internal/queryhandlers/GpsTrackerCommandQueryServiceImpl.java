package com.chapaTuBus.webService.monitoringAndExecution.application.internal.queryhandlers;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.GpsTracker;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusLocationLog;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.queries.GetAllBusLocationLogsByGpsTrackerIdQuery;
import com.chapaTuBus.webService.monitoringAndExecution.domain.services.GpsTrackerQueryService;
import com.chapaTuBus.webService.monitoringAndExecution.infraestructure.repositories.jpa.GpsTrackerRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GpsTrackerCommandQueryServiceImpl implements GpsTrackerQueryService {
    private final GpsTrackerRepository gpsTrackerRepository;

    public GpsTrackerCommandQueryServiceImpl(GpsTrackerRepository gpsTrackerRepository) {
        this.gpsTrackerRepository = gpsTrackerRepository;
    }

    @Override
    public List<BusLocationLog> handle(GetAllBusLocationLogsByGpsTrackerIdQuery query) {
        Optional<GpsTracker> gpsTracker = gpsTrackerRepository.findById((long) query.gpsTrackerId());
        if(gpsTracker.isEmpty()) return Collections.emptyList();
        return gpsTracker.get().getBusLocationLogs();
    }
}
