package com.chapaTuBus.webService.planification.domain.model.entities;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bus_unit")
public class UnitBus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_schedule_id")
    private DepartureSchedule departureSchedule;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "transport_company_id")
    private TransportCompany transportCompany;

    private int user;

}
