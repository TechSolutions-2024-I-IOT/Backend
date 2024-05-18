package com.chapaTuBus.webService.userAccount.interfaces.rest.transform;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.interfaces.rest.resources.UserRegisteredResource;

public class UserRegisteredResourceFromEntityAssembler {
    public static UserRegisteredResource toResourceFromEntity(User entity){
        return new UserRegisteredResource(entity.getId(),entity.getEmail());
    }
}
