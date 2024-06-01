package com.chapaTuBus.webService.planification.application.internal.commandservices;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.domain.model.commands.bus.RegisterBusCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.departureSchedule.CreateDepartureScheduleCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.driver.RegisterDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.schedule.CreateScheduleCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.transportCompany.CreateTransportCompanyCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.unitBus.AssignUnitBusCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.*;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyCommandService;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.TransportCompanyRepository;
import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

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


        Optional<User> user= userRepository.findById(command.userId());

        if(user.isEmpty())return Optional.empty();

        TransportCompany transportCompany= new TransportCompany(user.get(),command);
        transportCompanyRepository.save(transportCompany);

        return Optional.of(transportCompany);
    }

    @Override
    public Optional<Bus> handle(RegisterBusCommand command) {


        Optional<User> userOpt= userRepository.findById((long) command.user());

        if(userOpt.isEmpty())return Optional.empty();

        Optional<TransportCompany> transportCompanyOpt=transportCompanyRepository.findById(userOpt.get().getTransportCompany().getId());

        if(transportCompanyOpt.isPresent()){
            TransportCompany transportCompany= transportCompanyOpt.get();
            transportCompany.registerNewBus(command);
            transportCompanyRepository.save(transportCompany);
            return transportCompany.getBuses().stream()
                    .filter(bus->bus.getLicensePlate().equals(command.licensePlate())).findFirst();
        }else{
            return Optional.empty();
        }


    }


    @Override
    public Optional<UnitBus> handle(AssignUnitBusCommand command) {

        Optional<User> userOpt = userRepository.findById((long) command.userId());

        if(userOpt.isEmpty()) return Optional.empty();

        Optional<TransportCompany> transportCompanyOpt= transportCompanyRepository.findById(userOpt.get().getTransportCompany().getId());


        //Optional<Driver> driverOpt= driverRepository.findById(command.driver().getId());
        Optional<Driver> driverOpt= transportCompanyRepository.findDriverByUserId(command.userId());

        //Optional<Bus> busOpt= busRepository.findById(command.bus().getId());

        Optional<Bus>busOpt= transportCompanyRepository.findBusByUserId(command.userId());

        boolean areAllEntitiesFound=transportCompanyOpt.isPresent() && driverOpt.isPresent() && busOpt.isPresent();

        if(areAllEntitiesFound){
            TransportCompany transportCompany= transportCompanyOpt.get();
            Driver driver= driverOpt.get();
            Bus bus= busOpt.get();

            AssignUnitBusCommand updatedCommand= new AssignUnitBusCommand(command.userId(), driver,bus);

            transportCompany.assignNewUnitBus(updatedCommand);

            transportCompanyRepository.save(transportCompany);

            return transportCompany.getUnitBuses().stream()
                    .filter(unitBus -> unitBus.getDriver().equals(updatedCommand.driver())).findFirst();
        }else{
            return Optional.empty();
        }

    }

    @Override
    public Optional<Schedule> handle(CreateScheduleCommand command) {

        Optional<User> userOpt= userRepository.findById((long) command.user());

        if(userOpt.isEmpty())return Optional.empty();

        Optional<TransportCompany> transportCompanyOpt= transportCompanyRepository.findById(userOpt.get().getTransportCompany().getId());

        if(transportCompanyOpt.isPresent()){
            TransportCompany transportCompany= transportCompanyOpt.get();
            transportCompany.createNewSchedule(command);
            transportCompanyRepository.save(transportCompany);
            return transportCompany.getSchedules().stream()
                    .filter(schedule -> schedule.getDate().equals(command.date())).findFirst();
        }else{
            return Optional.empty();
        }

    }

    @Override
    public Optional<DepartureSchedule> handle(CreateDepartureScheduleCommand command) {

        Optional<User> userOpt= userRepository.findById((long) command.user());

        if(userOpt.isEmpty()) return Optional.empty();

        Optional<TransportCompany> transportCompanyOpt= transportCompanyRepository.findById(userOpt.get().getTransportCompany().getId());

        if (transportCompanyOpt.isPresent()) {
            TransportCompany transportCompany = transportCompanyOpt.get();
            transportCompanyRepository.save(transportCompany);

            DepartureSchedule newDepartureSchedule = transportCompany.createNewDepartureSchedule(command);

            transportCompanyRepository.save(transportCompany);

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
            return transportCompany.getDrivers().stream().
                    filter(driver -> driver.getDni().equals(command.dni())).findFirst();
        }else{
            return Optional.empty();
        }

    }


}
