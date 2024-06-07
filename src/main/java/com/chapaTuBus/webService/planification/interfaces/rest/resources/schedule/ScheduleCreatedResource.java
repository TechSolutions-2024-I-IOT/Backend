package com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule;

public record ScheduleCreatedResource(
        Long id,
        String date,
        String description,
        int user,
        boolean isDeleted
) {
}
