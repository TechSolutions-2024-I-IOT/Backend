package com.chapaTuBus.webService.planification.domain.model.queries;

public record GetAllDriversByUserIdQuery(int id) {

    public GetAllDriversByUserIdQuery {
        if (id <= 0) {
            throw new IllegalArgumentException("UserID null or negative");
        }
    }

}
