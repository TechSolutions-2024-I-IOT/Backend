package com.chapaTuBus.webService.userAccount.interfaces.rest.transform;

import com.chapaTuBus.webService.userAccount.domain.model.commands.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.domain.model.entities.Role;
import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.Roles;
import com.chapaTuBus.webService.userAccount.interfaces.rest.resources.RegisterUserResource;

public class RegisterUserCommandFromResourceAssembler {
    public static RegisterUserCommand toCommandFromResource(RegisterUserResource registerUserResource){
        Role role= Role.toRoleFromName(registerUserResource.role());
        return new RegisterUserCommand(registerUserResource.email(), registerUserResource.password(),role);
    }
}
