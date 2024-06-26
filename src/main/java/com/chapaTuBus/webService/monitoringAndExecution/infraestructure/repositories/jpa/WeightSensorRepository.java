package com.chapaTuBus.webService.monitoringAndExecution.infraestructure.repositories.jpa;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.WeightSensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightSensorRepository extends JpaRepository<WeightSensor,Long>{
}
