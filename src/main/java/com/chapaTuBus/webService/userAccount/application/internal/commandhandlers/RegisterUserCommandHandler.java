package com.chapaTuBus.webService.userAccount.application.internal.commandhandlers;

import com.chapaTuBus.webService.userAccount.domain.model.commands.auth.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.application.internal.commandservices.AuthenticationCommandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterUserCommandHandler{


    private final AuthenticationCommandServiceImpl authenticationService;

    @Autowired
    public RegisterUserCommandHandler(AuthenticationCommandServiceImpl authenticationService){
        this.authenticationService = authenticationService;
    }

    public void handle(RegisterUserCommand command){
        authenticationService.signUp(command);
    }


}
