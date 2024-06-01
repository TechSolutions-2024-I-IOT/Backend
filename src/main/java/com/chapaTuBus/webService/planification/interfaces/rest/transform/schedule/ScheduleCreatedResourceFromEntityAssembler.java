package com.chapaTuBus.webService.planification.interfaces.rest.transform.schedule;

import com.chapaTuBus.webService.planification.domain.model.entities.Schedule;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.ScheduleCreatedResource;

import java.time.format.DateTimeFormatter;

public class ScheduleCreatedResourceFromEntityAssembler {

    public static ScheduleCreatedResource toResourceFromEntity(Schedule schedule){

        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return new ScheduleCreatedResource(schedule.getId(),schedule.getDate().format(formatter),schedule.getDescription(),schedule.getUser());
    }
}
