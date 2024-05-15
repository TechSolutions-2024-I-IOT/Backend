package com.chapaTuBus.webService.userAccount.application.internal.commandhandlers;

import com.chapaTuBus.webService.userAccount.domain.model.commands.auth.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.application.internal.services.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterUserCommandHandler{


    private final AuthenticationServiceImpl authenticationService;

    @Autowired
    public RegisterUserCommandHandler(AuthenticationServiceImpl authenticationService){
        this.authenticationService = authenticationService;
    }

    public void handle(RegisterUserCommand command){
        authenticationService.signUp(command);
    }


}
