package com.chapaTuBus.webService.planification.domain.model.entities;

import com.chapaTuBus.webService.planification.domain.model.valueobjects.BusModel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;


    @ManyToOne
    private BusModel busModel;


}
