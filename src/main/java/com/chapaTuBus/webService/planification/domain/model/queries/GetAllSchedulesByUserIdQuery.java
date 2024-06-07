package com.chapaTuBus.webService.planification.domain.model.queries;

public record GetAllSchedulesByUserIdQuery(int userId) {
    public GetAllSchedulesByUserIdQuery {
        if (userId <= 0) {
            throw new IllegalArgumentException("UserId cannot be null or negative");
        }
    }
}
