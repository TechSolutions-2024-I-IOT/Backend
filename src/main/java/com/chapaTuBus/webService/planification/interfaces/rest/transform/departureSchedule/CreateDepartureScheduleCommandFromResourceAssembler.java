package com.chapaTuBus.webService.planification.interfaces.rest.transform.departureSchedule;

import com.chapaTuBus.webService.planification.domain.model.commands.departureSchedule.CreateDepartureScheduleCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.departureSchedule.CreateDepartureScheduleResource;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CreateDepartureScheduleCommandFromResourceAssembler {

    public static CreateDepartureScheduleCommand toCommand(CreateDepartureScheduleResource resource) {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Formato 24 horas
        LocalTime parsedTime = LocalTime.parse(resource.time(), timeFormatter);

        return new CreateDepartureScheduleCommand(
                parsedTime,
                resource.roundNumber(),
                resource.unitBusId(),
                resource.scheduleId(),
                resource.user()
        );
    }
}