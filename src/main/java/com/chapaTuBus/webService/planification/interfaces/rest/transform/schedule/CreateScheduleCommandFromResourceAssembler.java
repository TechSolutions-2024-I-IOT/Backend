package com.chapaTuBus.webService.planification.interfaces.rest.transform.schedule;

import com.chapaTuBus.webService.planification.domain.model.commands.schedule.CreateScheduleCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.CreateScheduleResource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateScheduleCommandFromResourceAssembler {

    public static CreateScheduleCommand toCommand(CreateScheduleResource createScheduleResource){

        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedDate = LocalDate.parse(createScheduleResource.date(),formatter);

        return new CreateScheduleCommand(
                parsedDate,
                createScheduleResource.description(),
                createScheduleResource.user()
        );
    }
}
