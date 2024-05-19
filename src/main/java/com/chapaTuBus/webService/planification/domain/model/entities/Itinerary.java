package com.chapaTuBus.webService.planification.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;

    private String destiny;

    private double total_distance;


    @OneToMany(mappedBy = "itinerary",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Stop>stops;

}
