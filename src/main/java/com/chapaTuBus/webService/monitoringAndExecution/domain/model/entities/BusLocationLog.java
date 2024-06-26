package com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.GpsTracker;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BusLocationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String latitude;

    private String longitude;

    private String speed;

    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    @ManyToOne
    @JoinColumn(name = "gps_tracker_id")
    private GpsTracker gpsTracker;
}
