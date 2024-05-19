package com.chapaTuBus.webService.planification.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Departure {

    @Id
    @GeneratedValue
    private Long id;

    private int roundNumber;

    private Date departureTime;

    @ManyToOne
    @JoinColumn(name = "departure_schedule_id")
    private DepartureSchedule departureSchedule;

}
