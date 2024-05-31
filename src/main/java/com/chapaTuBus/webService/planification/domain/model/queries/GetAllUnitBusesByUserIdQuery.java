package com.chapaTuBus.webService.planification.domain.model.queries;

public record GetAllUnitBusesByUserIdQuery(int id){
    public GetAllUnitBusesByUserIdQuery {
        if (id <= 0) {
            throw new IllegalArgumentException("UserID cannot be null or negative");
        }
    }
}
