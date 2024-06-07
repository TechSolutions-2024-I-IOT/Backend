package com.chapaTuBus.webService.userAccount.interfaces.rest.transform;

import com.chapaTuBus.webService.userAccount.domain.model.commands.users.ModifyProfileCommand;
import com.chapaTuBus.webService.userAccount.interfaces.rest.resources.ModifyProfileResource;

public class ModifyProfileCommandFromResourceAssembler {

    public static ModifyProfileCommand toCommand(Long userId,ModifyProfileResource resource){
        return new ModifyProfileCommand(
                userId,
                resource.firstName(),
                resource.lastName(),
                resource.photoUrl()
        );
    }
}
