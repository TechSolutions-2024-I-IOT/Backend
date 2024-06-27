package com.chapaTuBus.webService.planification.domain.model.entities;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.GpsTracker;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates.SmartBand;
import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String driverLicenseNumber;

    private String dni;

    private String photoUrl;

    private String phoneNumber;

    private String email;

    private int user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "smart_band_id")
    private SmartBand smartBand;

    @ManyToOne
    @JoinColumn(name = "transport_company_id")
    private TransportCompany transportCompany;

    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean isDeleted;

}


