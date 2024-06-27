package com.chapaTuBus.webService.monitoringAndExecution.application.internal.commandhandlers;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.WeightSensor;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.CreateWeightSensorCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.RegisterBusCapacityCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusCapacity;
import com.chapaTuBus.webService.monitoringAndExecution.domain.services.WeightSensorCommandService;
import com.chapaTuBus.webService.monitoringAndExecution.infraestructure.repositories.jpa.WeightSensorRepository;
import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.TransportCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class WeightSensorCommandServiceImpl implements WeightSensorCommandService {

    private final WeightSensorRepository weightSensorRepository;
    private final TransportCompanyRepository transportCompanyRepository;

    public WeightSensorCommandServiceImpl(WeightSensorRepository weightSensorRepository, TransportCompanyRepository transportCompanyRepository) {
        this.weightSensorRepository = weightSensorRepository;
        this.transportCompanyRepository = transportCompanyRepository;
    }

    @Override
    public Optional<WeightSensor> handle(CreateWeightSensorCommand command) {
        Optional<UnitBus> unitBusOpt = transportCompanyRepository.findUnitBusById(Math.toIntExact(command.unitBusId()));
        if (unitBusOpt.isEmpty()) return Optional.empty();

        UnitBus unitBus = unitBusOpt.get();
        if (unitBus.getWeightSensor() != null) return Optional.empty();

        WeightSensor weightSensor = new WeightSensor(command);
        weightSensorRepository.save(weightSensor);

        unitBus.setWeightSensor(weightSensor);
        weightSensor.setUnitBusId(unitBus.getId().intValue());

        transportCompanyRepository.save(unitBus.getTransportCompany());

        return Optional.of(weightSensor);
    }

    @Override
    public Optional<BusCapacity> handle(RegisterBusCapacityCommand command) {
        Optional<WeightSensor> weightSensorOpt = weightSensorRepository.findById(command.weightSensorId());
        if (weightSensorOpt.isEmpty()) return Optional.empty();

        WeightSensor weightSensor = weightSensorOpt.get();

        BusCapacity busCapacity = weightSensor.registerNewBusCapacity(command);

        weightSensorRepository.save(weightSensor);

        BusCapacity savedBusCapacity = weightSensor.getBusCapacities().stream()
                .filter(bc -> bc.getTimeStamp().equals(busCapacity.getTimeStamp()))
                .findFirst()
                .orElse(busCapacity);

        return Optional.of(busCapacity);
    }
}
