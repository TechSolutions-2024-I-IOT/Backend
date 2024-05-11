package com.chapaTuBus.webService.userAccount.interfaces.rest;

import com.chapaTuBus.webService.userAccount.application.internal.commandhandlers.RegisterUserCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UsersController {

    private final RegisterUserCommandHandler registerUserCommandHandler;

    public UsersController(RegisterUserCommandHandler registerUserCommandHandler) {
        this.registerUserCommandHandler = registerUserCommandHandler;
    }


    public ResponseEntity<>


}
