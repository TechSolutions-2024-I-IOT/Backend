package com.chapaTuBus.webService.planification.domain.model.queries;

public record GetAllDriversByTransportCompanyIdQuery(Long id) {

    public GetAllDriversByTransportCompanyIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("TransportCompanyID cannot be null or negative");
        }
    }

}
