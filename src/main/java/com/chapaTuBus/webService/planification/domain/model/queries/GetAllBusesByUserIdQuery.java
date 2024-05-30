package com.chapaTuBus.webService.planification.domain.model.queries;

public record GetAllBusesByUserIdQuery(int id) {

    public GetAllBusesByUserIdQuery {
        if(id<=0){
            throw new IllegalArgumentException("UserId cannot be null or negative");
        }
    }
}
