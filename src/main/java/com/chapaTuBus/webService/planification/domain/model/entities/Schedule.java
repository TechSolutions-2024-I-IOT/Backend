package com.chapaTuBus.webService.planification.domain.model.entities;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private LocalDate date;

    private String description;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepartureSchedule> departureSchedules;

    @ManyToOne
    @JoinColumn(name = "transport_company_id")
    private TransportCompany transportCompany;

    private int user;

    public Schedule() {
        this.departureSchedules = new ArrayList<>();
    }


}
