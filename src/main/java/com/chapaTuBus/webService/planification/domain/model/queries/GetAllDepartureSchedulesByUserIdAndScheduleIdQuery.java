package com.chapaTuBus.webService.planification.domain.model.queries;

public record GetAllDepartureSchedulesByUserIdAndScheduleIdQuery(
        int userId,
        int scheduleId
) {
}
