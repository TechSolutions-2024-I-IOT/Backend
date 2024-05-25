package com.chapaTuBus.webService.planification.application.internal.commandservices;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.domain.model.commands.driver.RegisterDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.transportCompany.CreateTransportCompanyCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
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
    public Optional<Driver> handle(RegisterDriverCommand command) {

        Optional<TransportCompany> transportCompanyOpt= transportCompanyRepository.findById(command.transportCompanyId());

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
