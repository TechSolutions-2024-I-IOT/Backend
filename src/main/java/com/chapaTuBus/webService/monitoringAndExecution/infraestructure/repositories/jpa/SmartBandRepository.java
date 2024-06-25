package com.chapaTuBus.webService.monitoringAndExecution.infraestructure.repositories.jpa;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.SmartBand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.HeartRateLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmartBandRepository extends JpaRepository<SmartBand,Long> {
}
