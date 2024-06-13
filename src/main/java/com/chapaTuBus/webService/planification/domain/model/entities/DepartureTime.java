package com.chapaTuBus.webService.planification.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartureTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "departure_schedule_id")
    private DepartureSchedule departureSchedule;
}