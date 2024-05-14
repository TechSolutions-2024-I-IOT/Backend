package com.chapaTuBus.webService.userAccount.application.internal.commandhandlers;

import com.chapaTuBus.webService.userAccount.domain.model.commands.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.domain.services.AuthenticationService;
import com.chapaTuBus.webService.userAccount.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterUserCommandHandler{


    private final AuthenticationService authenticationService;

    @Autowired
    public RegisterUserCommandHandler(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    public void handle(RegisterUserCommand command){
        authenticationService.signUp(command);
    }


}
