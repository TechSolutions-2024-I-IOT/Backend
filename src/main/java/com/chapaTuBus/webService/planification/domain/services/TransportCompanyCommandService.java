package com.chapaTuBus.webService.planification.domain.services;

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
import com.chapaTuBus.webService.planification.domain.model.entities.*;

import java.util.Optional;

public interface TransportCompanyCommandService {
    Optional<Driver> handle(RegisterDriverCommand command);
    Optional<TransportCompany> handle(CreateTransportCompanyCommand command);
    Optional<Bus> handle(RegisterBusCommand command);
    Optional<UnitBus> handle(AssignUnitBusCommand command);
    Optional<Schedule>handle(CreateScheduleCommand command);
    Optional<DepartureSchedule>handle(CreateDepartureScheduleCommand command);
    Optional<Driver> handle(ModifyDriverCommand command);
    Optional<Bus> handle(ModifyBusCommand command);
    Optional<Driver> handle(DeleteDriverCommand command);
    Optional<Bus> handle(DeleteBusCommand command);
}
