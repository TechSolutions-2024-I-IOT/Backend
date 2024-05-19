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

    @OneToMany(mappedBy = "departureSchedule",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Departure> departures;

    @ManyToOne
    @JoinColumn(name = "bus_unit_id")
    private UnitBus unitBus;

}
