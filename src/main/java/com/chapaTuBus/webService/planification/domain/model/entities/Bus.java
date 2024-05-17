package com.chapaTuBus.webService.planification.domain.model.entities;

import com.chapaTuBus.webService.planification.domain.model.valueobjects.BusModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;

    
    private BusModel busModel;
}
