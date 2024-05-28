package com.chapaTuBus.webService.planification.domain.model.queries;

public record GetAllUnitBusesByTransportCompanyIdQuery (Long id){
    public GetAllUnitBusesByTransportCompanyIdQuery{
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("TransportCompanyID cannot be null or negative");
        }
    }
}
