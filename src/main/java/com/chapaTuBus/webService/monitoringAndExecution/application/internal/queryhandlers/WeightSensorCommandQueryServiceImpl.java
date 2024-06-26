package com.chapaTuBus.webService.monitoringAndExecution.application.internal.queryhandlers;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.WeightSensor;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusCapacity;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.queries.GetBusCapacityByWeightSensorIdQuery;
import com.chapaTuBus.webService.monitoringAndExecution.domain.services.WeightSensorQueryService;
import com.chapaTuBus.webService.monitoringAndExecution.infraestructure.repositories.jpa.WeightSensorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WeightSensorCommandQueryServiceImpl implements WeightSensorQueryService {

    private final WeightSensorRepository weightSensorRepository;

    public WeightSensorCommandQueryServiceImpl(WeightSensorRepository weightSensorRepository) {
        this.weightSensorRepository = weightSensorRepository;
    }

    @Override
    public List<BusCapacity> handle(GetBusCapacityByWeightSensorIdQuery query) {

        Optional<WeightSensor> weightSensor= weightSensorRepository.findById((long) query.weightSensorId());
        if(weightSensor.isEmpty())return Collections.emptyList();
        return weightSensor.get().getBusCapacities();
    }
}
