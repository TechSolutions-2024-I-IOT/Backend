package com.chapaTuBus.webService.planification.application.internal.commandservices;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.domain.model.commands.driver.RegisterDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.transportCompany.CreateTransportCompanyCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyCommandService;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.TransportCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransportCompanyCommandServiceImpl implements TransportCompanyCommandService {

    private final TransportCompanyRepository transportCompanyRepository;

    public TransportCompanyCommandServiceImpl(TransportCompanyRepository transportCompanyRepository) {
        this.transportCompanyRepository = transportCompanyRepository;
    }

    @Override
    public Optional<TransportCompany> handle(CreateTransportCompanyCommand command) {

        //Optional<TransportCompany> transportCompany
        //Cuando se crea debe saber a cual user referenciarse , como hago eso?

        return Optional.empty();
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
