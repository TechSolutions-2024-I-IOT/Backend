package com.chapaTuBus.webService.planification.domain.model.valueobjects;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;
    private String email;
    private String secondaryEmail;
    private String emergencyContactNumber;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;



}
