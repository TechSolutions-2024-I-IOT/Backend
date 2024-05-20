package com.chapaTuBus.webService.planification.domain.model.entities;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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

    @ManyToOne
    @JoinColumn(name = "transport_company_id")
    private TransportCompany transportCompany;

    public Driver() {
    }

}


