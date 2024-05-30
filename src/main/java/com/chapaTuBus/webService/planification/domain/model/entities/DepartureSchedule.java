package com.chapaTuBus.webService.planification.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class DepartureSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date departureDate;

    private Date shiftStart;

    @ManyToOne
    @JoinColumn(name = "departure_id",nullable = false)
    private Departure departure;

    @ManyToOne
    @JoinColumn(name = "bus_unit_id")
    private UnitBus unitBus;

}
