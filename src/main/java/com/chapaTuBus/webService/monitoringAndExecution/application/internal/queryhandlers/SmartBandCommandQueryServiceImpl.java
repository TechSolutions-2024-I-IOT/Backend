package com.chapaTuBus.webService.monitoringAndExecution.application.internal.queryhandlers;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.SmartBand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.HeartRateLog;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.queries.GetAllHeartRateLogsBySmartBandIdQuery;
import com.chapaTuBus.webService.monitoringAndExecution.domain.services.SmartBandQueryService;
import com.chapaTuBus.webService.monitoringAndExecution.infraestructure.repositories.jpa.SmartBandRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SmartBandCommandQueryServiceImpl implements SmartBandQueryService {

    private final SmartBandRepository smartBandRepository;

    public SmartBandCommandQueryServiceImpl(SmartBandRepository smartBandRepository) {
        this.smartBandRepository = smartBandRepository;
    }

    @Override
    public List<HeartRateLog> handle(GetAllHeartRateLogsBySmartBandIdQuery query) {

        Optional<SmartBand> smartBand= smartBandRepository.findById((long) query.smartBandId());
        if(smartBand.isEmpty())return Collections.emptyList();
        return smartBand.get().getHeartRateLogs();
    }
}
