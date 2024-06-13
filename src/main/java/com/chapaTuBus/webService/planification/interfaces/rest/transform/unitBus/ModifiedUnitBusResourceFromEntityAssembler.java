package com.chapaTuBus.webService.planification.interfaces.rest.transform.unitBus;

import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.ModifiedUnitBusResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.BusDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.DriverDto;

import java.util.Optional;

public class ModifiedUnitBusResourceFromEntityAssembler {
    public static ModifiedUnitBusResource toResourceFromEntity(UnitBus entity){
        DriverDto driverDto = null;
        BusDto busDto = null;

        Optional<Driver> driver = Optional.ofNullable(entity.getDriver());
        if (driver.isPresent()) {
            driverDto = new DriverDto(entity.getDriver().getId(),entity.getDriver().getFirstName(),entity.getDriver().getLastName());
        }else{
            driverDto = new DriverDto(null, null, null);
        }

        Optional<Bus> bus = Optional.ofNullable(entity.getBus());
        if(bus.isPresent()){
            busDto = new BusDto(entity.getBus().getId(), entity.getBus().getLicensePlate());
        }else{
            busDto = new BusDto(null, null);
        }

        return new ModifiedUnitBusResource(
                entity.getId(),
                driverDto,
                busDto,
                entity.getUser(),
                entity.isDeleted()
        );
    }
}
