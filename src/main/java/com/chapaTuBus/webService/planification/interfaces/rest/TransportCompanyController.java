package com.chapaTuBus.webService.planification.interfaces.rest;

import com.chapaTuBus.webService.planification.domain.model.commands.RegisterDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyCommandService;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.DriverRegisteredResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.RegisterDriverResource;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.DriverResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.RegisterDriverCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        //Falta hacerlo Response Entity
    ResponseEntity<DriverRegisteredResource> registerDriver(@RequestBody RegisterDriverResource registerDriverResource){

        //Intitialize Driver
        Optional<Driver> driver= transportCompanyCommandService.
                //Ejecutamos el comando
                handle(RegisterDriverCommandFromResourceAssembler.toCommand(registerDriverResource));

        //Devolvemos
        return driver.map(actualDriver->
                new ResponseEntity<>(DriverResourceFromEntityAssembler.toResourceFromEntity(actualDriver),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
}