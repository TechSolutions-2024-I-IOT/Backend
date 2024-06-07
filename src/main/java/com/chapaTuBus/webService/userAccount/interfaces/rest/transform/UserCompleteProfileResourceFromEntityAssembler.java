package com.chapaTuBus.webService.userAccount.interfaces.rest.transform;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.interfaces.rest.resources.UserCompleteProfileResource;

public class UserCompleteProfileResourceFromEntityAssembler {

    public static UserCompleteProfileResource toResourceFromEntity(User entity){
        return new UserCompleteProfileResource(
                entity.getId(),
                entity.getEmail(),
                entity.getProfile().getFirst_name(),
                entity.getProfile().getLast_name(),
                entity.getProfile().getPhoto_url()
        );
    }
}
