package com.chapaTuBus.webService.planification.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Departure {

    @Id
    @GeneratedValue
    private Long id;

    private int roundNumber;

    private Date departureTime;

    @OneToMany(mappedBy = "departure")
    private List<DepartureSchedule> departureSchedules;

}
