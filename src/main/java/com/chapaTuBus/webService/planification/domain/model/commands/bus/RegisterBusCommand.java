package com.chapaTuBus.webService.planification.domain.model.commands.bus;

import com.chapaTuBus.webService.planification.domain.model.valueobjects.BusStates;

import java.time.Year;

public record RegisterBusCommand(
        String licensePlate,
        int seatingCapacity,
        int totalCapacity,
        int year,
        BusStates state,
        int user
) {

    public RegisterBusCommand{
        int currentYear= Year.now().getValue();

        if(licensePlate==null || licensePlate.isEmpty()){
            throw new IllegalArgumentException("LicensePlate cannot be null or empty");
        }
        if(seatingCapacity<0 || seatingCapacity>200){
            throw new IllegalArgumentException("Seating Capacity Invalid");
        }
        if(totalCapacity<0 || totalCapacity>200){
            throw new IllegalArgumentException("Total Capacity Invalid");
        }
        if(year<1970 || year>currentYear){
            throw new IllegalArgumentException("Invalid year. Must be between 1970 and the current year.");
        }
        if(state==null){
            throw new IllegalArgumentException("Bus state cannot be null");
        }
        if(state!=BusStates.OPERATIVO && state!=BusStates.FUERA_DE_SERVICIO){
            throw new IllegalArgumentException("Invalid bus state. Allowed states: OPERATIVO, FUERA_DE_SERVICIO");
        }
    }
}
