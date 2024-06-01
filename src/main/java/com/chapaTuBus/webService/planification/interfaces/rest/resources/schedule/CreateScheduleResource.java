package com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule;

public record CreateScheduleResource(
        String date,
        String description,
        int user
) {
}
