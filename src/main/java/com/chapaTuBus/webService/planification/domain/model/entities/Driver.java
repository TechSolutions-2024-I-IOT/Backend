package com.chapaTuBus.webService.planification.domain.model.entities;

import com.chapaTuBus.webService.planification.domain.model.valueobjects.ContactInformation;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String driverLicenseNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_information_id",referencedColumnName = "id")
    private ContactInformation contactInformation;


}
