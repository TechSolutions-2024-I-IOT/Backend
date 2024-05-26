package com.chapaTuBus.webService.planification.domain.model.queries;

public record GetAllBusesByTransportCompanyIdQuery(Long id) {

    public GetAllBusesByTransportCompanyIdQuery{
        if(id==null || id<=0){
            throw new IllegalArgumentException("TransportCompanyID cannot be null or negative");
        }
    }
}
