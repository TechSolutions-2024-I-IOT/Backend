package com.chapaTuBus.webService.planification.domain.model.aggregates;

import com.chapaTuBus.webService.planification.domain.model.commands.bus.RegisterBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.driver.RegisterDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.transportCompany.CreateTransportCompanyCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.unitBus.AssignUnitBusCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.model.entities.Itinerary;
import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Entity
@Getter
@Setter
@Table(name = "transport_companies")
public class TransportCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String busImageUrl;
    private String logoImageUrl;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    @OneToMany(mappedBy = "transportCompany", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnitBus> unitBuses;

    @OneToMany(mappedBy = "transportCompany",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Driver>drivers;

    @OneToMany(mappedBy = "transportCompany",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Bus> buses;

    protected TransportCompany(){
        this.name= Strings.EMPTY;
        this.busImageUrl= Strings.EMPTY;
        this.logoImageUrl= Strings.EMPTY;
        this.description= Strings.EMPTY;
        this.itinerary= new Itinerary();
        this.unitBuses= new ArrayList<>();
        this.drivers=new ArrayList<>();
        this.buses= new ArrayList<>();
    }

    public TransportCompany (User user,CreateTransportCompanyCommand command){
        this.name=command.name();
        this.busImageUrl= command.busImageUrl();
        this.logoImageUrl= command.logoImageUrl();
        this.description= command.description();
        this.user= user;
    }

    public void registerNewDriver (RegisterDriverCommand command){
        Driver driver= Driver.builder()
                .dni(command.dni())
                .phoneNumber(command.phoneNumber())
                .photoUrl(command.photoUrl())
                .driverLicenseNumber(command.driverLicenseNumber())
                .email(command.email())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .transportCompany(this)
                .build();

        this.drivers.add(driver);

    }

    public void registerNewBus(RegisterBusCommand command){
        Bus bus = Bus.builder()
                .state(command.state())
                .year(command.year())
                .seatingCapacity(command.seatingCapacity())
                .licensePlate(command.licensePlate())
                .totalCapacity(command.totalCapacity())
                .transportCompany(this)
                .build();

        this.buses.add(bus);
    }

    public void assignNewUnitBus(AssignUnitBusCommand command){
        UnitBus unitBus= UnitBus.builder()
                .bus(command.bus())
                .driver(command.driver())
                .transportCompany(this)
                .build();

        this.unitBuses.add(unitBus);
    }


}
