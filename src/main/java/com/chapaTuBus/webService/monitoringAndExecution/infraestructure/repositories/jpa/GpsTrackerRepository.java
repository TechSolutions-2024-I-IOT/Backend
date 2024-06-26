package com.chapaTuBus.webService.monitoringAndExecution.infraestructure.repositories.jpa;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.GpsTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GpsTrackerRepository extends JpaRepository<GpsTracker, Long> {
}
