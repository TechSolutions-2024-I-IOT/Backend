package com.chapaTuBus.webService.planification.domain.model.aggregates;

import com.chapaTuBus.webService.planification.domain.model.commands.bus.RegisterBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.departureSchedule.CreateDepartureScheduleCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.driver.RegisterDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.schedule.CreateScheduleCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.transportCompany.CreateTransportCompanyCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.unitBus.AssignUnitBusCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.*;
import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Entity
@Getter
@Setter
@Table(name = "transport_companies")
public class TransportCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private String name;
    private String busImageUrl;
    private String logoImageUrl;
    private String description;

    @OneToOne(mappedBy = "transportCompany")
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

    @OneToMany(mappedBy = "transportCompany",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Schedule> schedules;

    public TransportCompany(){
        this.name= Strings.EMPTY;
        this.busImageUrl= Strings.EMPTY;
        this.logoImageUrl= Strings.EMPTY;
        this.description= Strings.EMPTY;
        this.itinerary= null;
        this.unitBuses= new ArrayList<>();
        this.drivers=new ArrayList<>();
        this.buses= new ArrayList<>();
        this.schedules=new ArrayList<>();
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
                .user(command.user())
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
                .user(command.user())
                .build();

        this.buses.add(bus);
    }

    public void assignNewUnitBus(AssignUnitBusCommand command){
        UnitBus unitBus= UnitBus.builder()
                .bus(command.bus())
                .driver(command.driver())
                .transportCompany(this)
                .user(command.userId())
                .build();

        this.unitBuses.add(unitBus);
    }


    public void createNewSchedule(CreateScheduleCommand command) {
        Schedule schedule= Schedule.builder()
                .date(command.date())
                .description(command.description())
                .transportCompany(this)
                .user(command.user())
                .departureSchedules(null)
                .build();

        this.schedules.add(schedule);
    }

    public DepartureSchedule createNewDepartureSchedule(CreateDepartureScheduleCommand command){

        Optional<Schedule> optionalSchedule = this.schedules.stream()
                .filter(s -> s.getId().equals((long)command.scheduleId()))
                .findFirst();

        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();

            Optional<UnitBus> optionalUnitBus = this.unitBuses.stream()
                    .filter(ub -> ub.getId().equals((long)command.unitBusId()))
                    .findFirst();

            if (optionalUnitBus.isPresent()) {
                UnitBus unitBus = optionalUnitBus.get();

                DepartureSchedule departureSchedule = DepartureSchedule.builder()
                        .departureTime(command.time())
                        .roundNumber(command.roundNumber())
                        .schedule(schedule)
                        .unitBus(unitBus)
                        .user(command.user())
                        .build();

                schedule.getDepartureSchedules().add(departureSchedule);

                // Actualizar la lista schedules de TransportCompany
                this.schedules = this.schedules.stream()
                        .map(s -> s.getId().equals(schedule.getId()) ? schedule : s)
                        .collect(Collectors.toList());

                departureSchedule.setSchedule(schedule);

                return departureSchedule;

            } else {
                throw new IllegalArgumentException("UnitBus not found with ID: " + command.unitBusId());
            }
        } else {
            throw new IllegalArgumentException("Schedule not found with ID: " + command.scheduleId());
        }
    }

}
