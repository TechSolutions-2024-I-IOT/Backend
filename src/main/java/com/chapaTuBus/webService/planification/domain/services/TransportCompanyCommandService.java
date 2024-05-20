package com.chapaTuBus.webService.planification.domain.services;

import com.chapaTuBus.webService.planification.domain.model.commands.RegisterDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;

import java.util.Optional;

public interface TransportCompanyCommandService {
    Optional<Driver> handle(RegisterDriverCommand command);
}
