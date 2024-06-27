package com.chapaTuBus.webService.planification.domain.model.commands.schedule;

import java.time.LocalDate;

public record CreateScheduleCommand(
        LocalDate date,
        String description,
        int user
        ) {

        public CreateScheduleCommand{
                if(description==null || description.isEmpty()){
                        throw new IllegalArgumentException("Description cannot be null or empty");
                }
        }
}
