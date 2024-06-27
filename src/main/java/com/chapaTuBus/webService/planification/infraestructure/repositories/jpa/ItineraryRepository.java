package com.chapaTuBus.webService.planification.infraestructure.repositories.jpa;

import com.chapaTuBus.webService.planification.domain.model.entities.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
}

