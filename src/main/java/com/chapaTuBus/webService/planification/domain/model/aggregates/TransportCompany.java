package com.chapaTuBus.webService.planification.domain.model.aggregates;

import com.chapaTuBus.webService.planification.domain.model.commands.bus.DeleteBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.bus.ModifyBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.bus.RegisterBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.departureSchedule.CreateDepartureScheduleCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.driver.DeleteDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.driver.ModifyDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.driver.RegisterDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.itinerary.CreateItineraryWithStopsCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.schedule.CreateScheduleCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.schedule.CreateScheduleWithDepartureSchedulesCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.transportCompany.CreateTransportCompanyCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.unitBus.AssignUnitBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.unitBus.DeleteUnitBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.unitBus.ModifyUnitBusCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.*;
import com.chapaTuBus.webService.planification.domain.model.valueobjects.BusStates;
import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.domain.model.entities.Role;
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

    @OneToOne(mappedBy = "transportCompany")
    private Itinerary itinerary;

    @OneToMany(mappedBy = "transportCompany", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnitBus> unitBuses;

    @OneToMany(mappedBy = "transportCompany",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Driver>drivers;

    @OneToMany(mappedBy = "transportCompany",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Bus> buses;

    @OneToMany(mappedBy = "transportCompany",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Schedule> schedules;

    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean isDeleted;

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

    public Bus registerNewBus(RegisterBusCommand command) {
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
        return bus;
    }


    public UnitBus assignNewUnitBus(AssignUnitBusCommand command){
        UnitBus unitBus= UnitBus.builder()
                .bus(command.bus())
                .driver(command.driver())
                .transportCompany(this)
                .user(command.userId())
                .build();

        this.unitBuses.add(unitBus);
        return unitBus;
    }
    public Schedule createNewScheduleWithDepartureSchedules(CreateScheduleWithDepartureSchedulesCommand command,List<DepartureSchedule> departureSchedules){
        Schedule schedule= Schedule.builder()
                .date(command.date())
                .description(command.description())
                .departureSchedules(departureSchedules)
                .user(command.user())
                .transportCompany(this)
                .build();

        this.schedules.add(schedule);
        return schedule;
    }

    public void modifyDriver(ModifyDriverCommand command){

        Optional<Driver> selectedDriver= this.getDrivers().stream()
                .filter(driver-> command.driverId().equals(driver.getId()))
                .findFirst();

        if(selectedDriver.isEmpty())return ;

        Driver driver = selectedDriver.get();
        driver.setDni(command.dni());
        driver.setDriverLicenseNumber(command.driverLicenseNumber());
        driver.setEmail(command.email());
        driver.setLastName(command.lastName());
        driver.setFirstName(command.firstName());
        driver.setPhoneNumber(command.phoneNumber());
        driver.setPhotoUrl(command.photoUrl());

    }

    public void modifyUnitBus(ModifyUnitBusCommand command){
        Optional<UnitBus> selectedUnitBus = this.getUnitBuses().stream()
                .filter(unitBus -> command.unitBusId().equals(unitBus.getId()))
                .findFirst();
        if (selectedUnitBus.isEmpty()) return;

        Optional<Bus> selectedBus = this.getBuses().stream()
                .filter(bus -> command.busID().equals(bus.getId()))
                .findFirst();
        if(selectedBus.isEmpty()) return;

        Optional<Driver> selectedDriver = this.getDrivers().stream()
                .filter(driver -> command.driverId().equals(driver.getId()))
                .findFirst();
        if(selectedDriver.isEmpty()) return;

        Bus bus = selectedBus.get();
        Driver driver = selectedDriver.get();

        UnitBus unitBus = selectedUnitBus.get();
        unitBus.setBus(bus);
        unitBus.setDriver(driver);
    }

    public void deleteDriver(DeleteDriverCommand command){

        Optional<Driver> selectedDriver= this.getDrivers().stream()
                .filter(driver-> command.driverId().equals(driver.getId()))
                .findFirst();

        if(selectedDriver.isEmpty())return ;

        Driver driver=selectedDriver.get();
        driver.setDeleted(true);

    }

    public void deleteBus(DeleteBusCommand command){

        Optional<Bus> selectedBus= this.getBuses().stream()
                .filter(bus-> command.busId().equals(bus.getId()))
                .findFirst();

        if(selectedBus.isEmpty())return;

        Bus bus= selectedBus.get();

        bus.setDeleted(true);


    }

    public void deleteUnitBus(DeleteUnitBusCommand command){
        Optional<UnitBus> selectedUnitBus = this.getUnitBuses().stream()
                .filter(unitBus -> command.unitBusId().equals(unitBus.getId()))
                .findFirst();
        if(selectedUnitBus.isEmpty()) return;
        UnitBus unitBus = selectedUnitBus.get();
        unitBus.setDeleted(true);
    }

    public void modifyBus(ModifyBusCommand command){

        Optional<Bus> selectedBus= this.getBuses().stream()
                .filter(bus-> command.busId().equals(bus.getId()))
                .findFirst();

        if(selectedBus.isEmpty())return;

        Bus bus= selectedBus.get();

        bus.setLicensePlate(command.licensePlate());
        bus.setSeatingCapacity(command.seatingCapacity());
        bus.setTotalCapacity(command.totalCapacity());
        bus.setYear(command.year());
        bus.setState(BusStates.valueOf(command.state()));

    }

    public Schedule createNewSchedule(CreateScheduleCommand command) {
        Schedule schedule= Schedule.builder()
                .date(command.date())
                .description(command.description())
                .transportCompany(this)
                .user(command.user())
                .departureSchedules(null)
                .build();

        this.schedules.add(schedule);
        return schedule;
    }

    public Itinerary createNewItineraryWithStops(CreateItineraryWithStopsCommand command) {
        Itinerary itinerary = Itinerary.builder()
                .startTime(command.startTime())
                .endTime(command.endTime())
                .stops(command.stops().stream()
                        .map(stopCommand -> Stop.builder()
                                .name(stopCommand.name())
                                .latitude(stopCommand.latitude())
                                .longitude(stopCommand.longitude())
                                .build())
                        .collect(Collectors.toList()))
                .transportCompany(this)
                .user(command.user())
                .build();

        this.itinerary = itinerary;
        return itinerary;
    }

    public DepartureSchedule createNewDepartureSchedule(CreateDepartureScheduleCommand command) {
        Optional<Schedule> optionalSchedule = this.schedules.stream()
                .filter(s -> s.getId().equals((long) command.scheduleId()))
                .findFirst();

        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();

            Optional<UnitBus> optionalUnitBus = this.unitBuses.stream()
                    .filter(ub -> ub.getId().equals((long) command.unitBusId()))
                    .findFirst();

            if (optionalUnitBus.isPresent()) {
                UnitBus unitBus = optionalUnitBus.get();

                DepartureSchedule departureSchedule = DepartureSchedule.builder()
                        //.departureTime(command.time())
                        .roundNumber(command.roundNumber())
                        .schedule(schedule)
                        .unitBus(unitBus)
                        .user(command.user())
                        .build();

                schedule.getDepartureSchedules().add(departureSchedule);

                // Guardar el Schedule o el DepartureSchedule en el repositorio
                return departureSchedule;

            } else {
                throw new IllegalArgumentException("UnitBus not found with ID: " + command.unitBusId());
            }
        } else {
            throw new IllegalArgumentException("Schedule not found with ID: " + command.scheduleId());
        }
    }




}
