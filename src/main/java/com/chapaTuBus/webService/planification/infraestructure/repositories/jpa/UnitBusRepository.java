package com.chapaTuBus.webService.planification.infraestructure.repositories.jpa;

import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitBusRepository extends JpaRepository<UnitBus,Long> {
    List<UnitBus> findAllByUser(int userId);
}
