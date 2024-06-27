package com.chapaTuBus.webService.planification.interfaces.rest.transform.bus;

import com.chapaTuBus.webService.planification.domain.model.commands.bus.RegisterBusCommand;
import com.chapaTuBus.webService.planification.domain.model.valueobjects.BusStates;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.RegisterBusResource;

public class RegisterBusCommandFromResourceAssembler {
    public static RegisterBusCommand toCommand(RegisterBusResource registerBusResource){

        //Modify String State to enum state

        BusStates busState;
        try {
            busState = BusStates.valueOf(registerBusResource.state().toUpperCase());
        } catch (IllegalArgumentException e) {
            // Handle invalid state string (e.g., throw an exception, use a default value, etc.)
            throw new IllegalArgumentException("Invalid bus state: " + registerBusResource.state());
        }

        return new RegisterBusCommand(
                registerBusResource.licensePlate(),
                registerBusResource.seatingCapacity(),
                registerBusResource.totalCapacity(),
                registerBusResource.year(),
                busState,
                registerBusResource.userId()
        );
    }
}
