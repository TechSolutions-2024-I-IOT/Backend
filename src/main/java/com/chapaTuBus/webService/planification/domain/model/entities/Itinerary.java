package com.chapaTuBus.webService.planification.domain.model.entities;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.GpsTracker;
import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
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
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime startTime;

    private LocalTime endTime;

    @OneToMany(mappedBy = "itinerary",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Stop> stops;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transport_company_id",referencedColumnName = "id")
    private TransportCompany transportCompany;

    private int user;

    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean isDeleted;

}
