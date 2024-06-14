package com.chapaTuBus.webService.planification.application.internal.commandservices;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.domain.model.commands.bus.DeleteBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.bus.ModifyBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.bus.RegisterBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.departureSchedule.CreateDepartureScheduleCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.driver.DeleteDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.driver.ModifyDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.driver.RegisterDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.schedule.CreateScheduleCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.transportCompany.CreateTransportCompanyCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.unitBus.AssignUnitBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.unitBus.DeleteUnitBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.unitBus.ModifyUnitBusCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.*;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyCommandService;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.TransportCompanyRepository;
import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class TransportCompanyCommandServiceImpl implements TransportCompanyCommandService {

    private final TransportCompanyRepository transportCompanyRepository;
    private final UserRepository userRepository;

    public TransportCompanyCommandServiceImpl(
            TransportCompanyRepository transportCompanyRepository,
            UserRepository userRepository) {
        this.transportCompanyRepository = transportCompanyRepository;
        this.userRepository=userRepository;
    }

    @Override
    public Optional<TransportCompany> handle(CreateTransportCompanyCommand command) {


        Optional<User> userOpt= userRepository.findById(command.userId());

        if(userOpt.isEmpty())return Optional.empty();

        User user = userOpt.get();
        TransportCompany transportCompany= new TransportCompany(user,command);
        transportCompanyRepository.save(transportCompany);
        user.setTransportCompany(transportCompany);
        userRepository.save(user);

        return Optional.of(transportCompany);
    }

    @Override
    public Optional<Bus> handle(RegisterBusCommand command) {


        Optional<User> userOpt= userRepository.findById((long) command.user());

        if(userOpt.isEmpty())return Optional.empty();

        Optional<TransportCompany> transportCompanyOpt=transportCompanyRepository.findById(userOpt.get().getTransportCompany().getId());

        if (transportCompanyOpt.isPresent()) {
            TransportCompany transportCompany = transportCompanyOpt.get();
            Bus newBus = transportCompany.registerNewBus(command);
            transportCompanyRepository.save(transportCompany);
            return Optional.of(newBus);
        } else {
            return Optional.empty();
        }


    }


    @Override
    public Optional<UnitBus> handle(AssignUnitBusCommand command) {
        Optional<User> userOpt = userRepository.findById((long) command.userId());
        if (userOpt.isEmpty()) return Optional.empty();

        TransportCompany transportCompany = userOpt.get().getTransportCompany();
        Optional<Driver> driverOpt = transportCompanyRepository.findDriverByIdAndTransportCompany(command.driver().getId().intValue(), transportCompany);
        Optional<Bus> busOpt = transportCompanyRepository.findBusById(command.bus().getId().intValue(), transportCompany);

        boolean areAllEntitiesFound = driverOpt.isPresent() && busOpt.isPresent();

        if (areAllEntitiesFound) {
            Driver driver = driverOpt.get();
            Bus bus = busOpt.get();

            AssignUnitBusCommand updatedCommand = new AssignUnitBusCommand(command.userId(), driver, bus);
            UnitBus newUnitBus = transportCompany.assignNewUnitBus(updatedCommand);
            transportCompanyRepository.save(transportCompany);

            return Optional.of(newUnitBus);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UnitBus> handle(ModifyUnitBusCommand command){
        Optional<TransportCompany> transportCompanyOpt = transportCompanyRepository.findByUserId((long) command.userId());
        if (transportCompanyOpt.isEmpty()) return Optional.empty();

        TransportCompany transportCompany = transportCompanyOpt.get();
        transportCompany.modifyUnitBus(command);
        transportCompanyRepository.save(transportCompany);

        return transportCompany.getUnitBuses().stream()
                .filter(unitBus -> unitBus.getId().equals(command.unitBusId()))
                .findFirst();
    }

    @Override
    public Optional<UnitBus> handle(DeleteUnitBusCommand command){
        Optional<UnitBus> unitBus = transportCompanyRepository.findUnitBusById(Math.toIntExact(command.unitBusId()));
        if(unitBus.isEmpty()) return Optional.empty();

        Optional<TransportCompany> transportCompanyOpt = transportCompanyRepository.findByUserId((long) unitBus.get().getUser());
        if(transportCompanyOpt.isEmpty()) return Optional.empty();

        TransportCompany transportCompany = transportCompanyOpt.get();
        transportCompany.deleteUnitBus(command);
        transportCompanyRepository.save(transportCompany);
        return transportCompany.getUnitBuses().stream()
                .filter(actualUnitBus -> actualUnitBus.getId().equals(command.unitBusId()))
                .findFirst();
    }

    @Override
    public Optional<Schedule> handle(CreateScheduleCommand command) {

        Optional<User> userOpt= userRepository.findById((long) command.user());

        if(userOpt.isEmpty())return Optional.empty();

        Optional<TransportCompany> transportCompanyOpt= transportCompanyRepository.findById(userOpt.get().getTransportCompany().getId());

        if(transportCompanyOpt.isPresent()){
            TransportCompany transportCompany= transportCompanyOpt.get();
            Schedule newSchedule = transportCompany.createNewSchedule(command);
            transportCompanyRepository.save(transportCompany);
            return Optional.of(newSchedule);
        }else{
            return Optional.empty();
        }

    }

    @Transactional
    @Override
    public Optional<DepartureSchedule> handle(CreateDepartureScheduleCommand command) {
        Optional<User> userOpt = userRepository.findById((long) command.user());

        if (userOpt.isEmpty()) return Optional.empty();

        Optional<TransportCompany> transportCompanyOpt = transportCompanyRepository.findById(userOpt.get().getTransportCompany().getId());

        if (transportCompanyOpt.isPresent()) {
            TransportCompany transportCompany = transportCompanyOpt.get();

            DepartureSchedule newDepartureSchedule = transportCompany.createNewDepartureSchedule(command);

            transportCompanyRepository.saveAndFlush(transportCompany); // Save and flush to ensure the ID is assigned

            return Optional.of(newDepartureSchedule);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Driver> handle(RegisterDriverCommand command) {

        Optional<User> userOpt = userRepository.findById((long) command.user());

        if(userOpt.isEmpty()){
            return Optional.empty();
        }

        Optional<TransportCompany> transportCompanyOpt= transportCompanyRepository.findById(userOpt.get().getTransportCompany().getId());

        if(transportCompanyOpt.isPresent()){
            TransportCompany transportCompany= transportCompanyOpt.get();
            transportCompany.registerNewDriver(command);
            transportCompanyRepository.save(transportCompany);
            Driver newlyRegisteredDriver = transportCompany.getDrivers().get(transportCompany.getDrivers().size() - 1);
            return Optional.of(newlyRegisteredDriver);
        }else{
            return Optional.empty();
        }

    }

    @Override
    public Optional<Driver> handle(ModifyDriverCommand command) {
        Optional<TransportCompany> transportCompanyOpt = transportCompanyRepository.findByUserId((long) command.userId());
        if (transportCompanyOpt.isEmpty()) return Optional.empty();

        TransportCompany transportCompany = transportCompanyOpt.get();
        transportCompany.modifyDriver(command);
        transportCompanyRepository.save(transportCompany);

        return transportCompany.getDrivers().stream()
                .filter(driver -> driver.getId().equals(command.driverId()))
                .findFirst();
    }

    @Override
    public Optional<Bus> handle(ModifyBusCommand command) {

        Optional<TransportCompany> transportCompanyOpt= transportCompanyRepository.findByUserId((long) command.userId());
        if (transportCompanyOpt.isEmpty()) return Optional.empty();

        TransportCompany transportCompany = transportCompanyOpt.get();

        transportCompany.modifyBus(command);

        transportCompanyRepository.save(transportCompany);

        return transportCompany.getBuses().stream()
                .filter(bus-> bus.getId().equals(command.busId()))
                .findFirst();
    }

    @Override
    public Optional<Driver> handle(DeleteDriverCommand command) {

        Optional<Driver> driver= transportCompanyRepository.findDriverById(Math.toIntExact(command.driverId()));

        if(driver.isEmpty())return Optional.empty();

        Optional<TransportCompany> transportCompanyOpt= transportCompanyRepository.findByUserId((long) driver.get().getUser());

        if (transportCompanyOpt.isEmpty()) return Optional.empty();

        TransportCompany transportCompany= transportCompanyOpt.get();

        transportCompany.deleteDriver(command);

        transportCompanyRepository.save(transportCompany);

        return transportCompany.getDrivers().stream()
                .filter(actualDriver->actualDriver.getId().equals(command.driverId()))
                .findFirst();

    }

    @Override
    public Optional<Bus> handle(DeleteBusCommand command) {

        Optional<Bus> bus= transportCompanyRepository.findBusById(Math.toIntExact(command.busId()));

        if(bus.isEmpty())return Optional.empty();

        Optional<TransportCompany> transportCompanyOpt= transportCompanyRepository.findByUserId((long) bus.get().getUser());

        if (transportCompanyOpt.isEmpty()) return Optional.empty();

        TransportCompany transportCompany= transportCompanyOpt.get();

        transportCompany.deleteBus(command);

        transportCompanyRepository.save(transportCompany);

        return transportCompany.getBuses().stream()
                .filter(actualBus->actualBus.getId().equals(command.busId()))
                .findFirst();
    }


}
