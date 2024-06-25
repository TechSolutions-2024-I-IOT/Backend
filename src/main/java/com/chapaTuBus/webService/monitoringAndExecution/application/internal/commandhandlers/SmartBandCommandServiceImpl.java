package com.chapaTuBus.webService.monitoringAndExecution.application.internal.commandhandlers;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.SmartBand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.CreateSmartBandCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.RegisterHeartRateLogCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.HeartRateLog;
import com.chapaTuBus.webService.monitoringAndExecution.domain.services.SmartBandCommandService;
import com.chapaTuBus.webService.monitoringAndExecution.infraestructure.repositories.jpa.SmartBandRepository;
import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.TransportCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SmartBandCommandServiceImpl implements SmartBandCommandService {

    private final SmartBandRepository smartBandRepository;
    private final TransportCompanyRepository transportCompanyRepository;

    public SmartBandCommandServiceImpl(SmartBandRepository smartBandRepository, TransportCompanyRepository transportCompanyRepository) {
        this.smartBandRepository = smartBandRepository;
        this.transportCompanyRepository = transportCompanyRepository;
    }

    @Override
    public Optional<SmartBand> handle(CreateSmartBandCommand command) {
        // Buscar el Driver por su ID
        Optional<Driver> driverOpt = transportCompanyRepository.findDriverById(Math.toIntExact(command.driverId()));
        if (driverOpt.isEmpty()) return Optional.empty();

        Driver driver = driverOpt.get();
        if (driver.getSmartBand() != null) return Optional.empty(); // Verificar si ya tiene una SmartBand asignada

        // Crear una nueva SmartBand
        SmartBand smartBand = new SmartBand(command);
        smartBandRepository.save(smartBand);

        // Asignar la SmartBand al Driver
        driver.setSmartBand(smartBand);
        smartBand.setDriverId(driver.getId().intValue());

        // Guardar el Driver actualizado
        transportCompanyRepository.save(driver.getTransportCompany());

        return Optional.of(smartBand);
    }

    @Override
    public Optional<HeartRateLog> handle(RegisterHeartRateLogCommand command) {
        // Buscar la SmartBand por su ID
        Optional<SmartBand> smartBandOpt = smartBandRepository.findById(command.smartBandId());
        if (smartBandOpt.isEmpty()) return Optional.empty();

        SmartBand smartBand = smartBandOpt.get();

        // Registrar un nuevo HeartRateLog usando el método del agregado
        HeartRateLog heartRateLog = smartBand.registerNewHeartRateLog(command);

        // Guardar la SmartBand con el nuevo HeartRateLog
        smartBandRepository.save(smartBand);

        // Recuperar el HeartRateLog guardado con el ID generado
        HeartRateLog savedHeartRateLog = smartBand.getHeartRateLogs().stream()
                .filter(log -> log.getTimeStamp().equals(heartRateLog.getTimeStamp()))
                .findFirst()
                .orElse(heartRateLog);

        return Optional.of(savedHeartRateLog);
    }
}
