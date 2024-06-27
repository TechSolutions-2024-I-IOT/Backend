package com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.WeightSensor;
import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BusCapacity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int busCapacity;

    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    @ManyToOne
    @JoinColumn(name = "weight_sensor_id")
    private WeightSensor weightSensor;
}
