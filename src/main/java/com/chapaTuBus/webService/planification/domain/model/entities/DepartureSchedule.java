package com.chapaTuBus.webService.planification.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DepartureSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "departureSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepartureTime> departureTimes;

    private int roundNumber;

    @ManyToOne
    @JoinColumn(name = "schedule_id",nullable = false)
    private Schedule schedule;


    @ManyToOne
    @JoinColumn(name = "bus_unit_id",nullable = false)
    private UnitBus unitBus;

    private int user;

    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean isDeleted;

}
