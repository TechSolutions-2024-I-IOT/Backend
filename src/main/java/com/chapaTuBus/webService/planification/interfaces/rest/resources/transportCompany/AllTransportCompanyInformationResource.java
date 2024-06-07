package com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany;

import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.DTO.*;

import java.util.List;

public record AllTransportCompanyInformationResource
        (
                Long id,
                String name,
                String busImageUrl,
                String logoImageUrl,
                String description,
                UserDto user,
                List<BusDto> buses,
                List<DriverDto> drivers,
                List<UnitBusDto> unitBuses,
                List<ScheduleDto> schedules,

                boolean isDeleted)
{
}
