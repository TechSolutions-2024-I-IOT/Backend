package com.chapaTuBus.webService.planification.domain.model.entities;

import jakarta.persistence.*;

@Entity
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String district;

    private String latitude;

    private String longitude;


    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean isDeleted;
}
