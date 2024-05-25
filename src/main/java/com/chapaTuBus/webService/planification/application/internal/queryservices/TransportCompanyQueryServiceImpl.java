package com.chapaTuBus.webService.planification.application.internal.queryservices;

import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllDriversByTransportCompanyIdQuery;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyQueryService;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.DriverRepository;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.TransportCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TransportCompanyQueryServiceImpl implements TransportCompanyQueryService {


    private final TransportCompanyRepository transportCompanyRepository;
    private final DriverRepository driverRepository;

    public TransportCompanyQueryServiceImpl(
            TransportCompanyRepository transportCompanyRepository,
            DriverRepository driverRepository) {
        this.transportCompanyRepository = transportCompanyRepository;
        this.driverRepository=driverRepository;
    }

    @Override
    public List<Driver> handle(GetAllDriversByTransportCompanyIdQuery query) {
        return driverRepository.findAllByTransportCompanyId(query.id());
    }
}
