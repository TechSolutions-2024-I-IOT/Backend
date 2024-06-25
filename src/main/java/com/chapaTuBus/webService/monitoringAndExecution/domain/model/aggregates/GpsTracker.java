package com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "gps_tracker")
public class GpsTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean isDeleted;

}
