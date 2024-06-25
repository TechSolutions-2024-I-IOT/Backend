package com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.SmartBand;
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
public class HeartRateLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int heartRate;

    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    @ManyToOne
    @JoinColumn(name = "smart_band_id")
    private SmartBand smartBand;

}
