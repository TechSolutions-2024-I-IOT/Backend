package com.chapaTuBus.webService.planification.domain.model.commands.schedule;

import com.chapaTuBus.webService.planification.domain.model.entities.DepartureSchedule;

import java.time.LocalDate;
import java.util.List;

public record CreateScheduleWithDepartureSchedulesCommand (
    LocalDate date,
    String description,
    List<DepartureScheduleCommand> departureSchedules,
    int user
){
    public CreateScheduleWithDepartureSchedulesCommand{
        if(description==null || description.isEmpty()){
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if(departureSchedules==null || departureSchedules.isEmpty()){
            throw new IllegalArgumentException("DepartureSchedules cannot be null or empty");
        }
    }
}
