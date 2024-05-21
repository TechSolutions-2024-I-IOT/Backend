package com.chapaTuBus.webService.planification.interfaces.rest;

import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyCommandService;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.DriverRegisteredResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.RegisterDriverResource;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.driver.DriverResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.driver.RegisterDriverCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/transport-company/")
public class TransportCompanyController {

    public final TransportCompanyCommandService transportCompanyCommandService;

    TransportCompanyController(TransportCompanyCommandService transportCompanyCommandService){
        this.transportCompanyCommandService = transportCompanyCommandService;
    }

    @PostMapping("drivers")
    ResponseEntity<DriverRegisteredResource> registerDriver(@RequestParam (name = "transportCompanyId") Long transportCompanyId, @RequestBody RegisterDriverResource registerDriverResource){


        Optional<Driver> driver= transportCompanyCommandService.
                //Execute the command
                handle(RegisterDriverCommandFromResourceAssembler.toCommand(transportCompanyId,registerDriverResource));

        //Return
        return driver.map(actualDriver->
                new ResponseEntity<>(DriverResourceFromEntityAssembler.toResourceFromEntity(actualDriver),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
}