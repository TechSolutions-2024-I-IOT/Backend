package com.chapaTuBus.webService.planification.infraestructure.repositories.jpa;

import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus,Long> {
    List<Bus> findAllByUser(int user);
}
