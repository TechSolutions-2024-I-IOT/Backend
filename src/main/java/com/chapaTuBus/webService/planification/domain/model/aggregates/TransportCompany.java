package com.chapaTuBus.webService.planification.domain.model.aggregates;

import com.chapaTuBus.webService.planification.domain.model.commands.RegisterDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.model.entities.Itinerary;
import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "transport_companies")
public class TransportCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String img_bus_url;
    private String logo_url;
    private String description;

    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    @OneToMany(mappedBy = "transportCompany", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnitBus> unitBuses;

    @OneToMany(mappedBy = "transportCompany")
    private List<Driver>drivers;

    protected TransportCompany(){
        this.name= Strings.EMPTY;
        this.img_bus_url= Strings.EMPTY;
        this.logo_url= Strings.EMPTY;
        this.description= Strings.EMPTY;
        this.itinerary= new Itinerary();
        this.unitBuses= new ArrayList<>();
        this.drivers=new ArrayList<>();
    }

    public void registerNewDriver (RegisterDriverCommand command){
        Driver driver= Driver.builder()
                .dni(command.dni())
                .driverLicenseNumber(command.driverLicenseNumber())
                .email(command.email())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .transportCompany(this)
                .build();

        this.drivers.add(driver);

    }


}
