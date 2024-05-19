package com.chapaTuBus.webService.planification.domain.model.valueobjects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class BusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelName;

    private String manufacturer;

    private int seatingCapacity;

    private int totalCapacity;

    private double lenght;

    private double height;

    private double width;

    private int year;

}
