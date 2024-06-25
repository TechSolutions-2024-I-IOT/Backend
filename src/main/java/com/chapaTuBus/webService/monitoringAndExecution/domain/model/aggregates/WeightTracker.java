package com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "weight_tracker")
public class WeightTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
}
