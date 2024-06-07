package com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.CompleteTransportCompanyInformationResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.DTO.UserDto;
import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;

import java.util.Optional;

public class CompleteTransportCompanyInformationResoruceFromEntityAssembler {
    public static CompleteTransportCompanyInformationResource toResourceFromEntity(TransportCompany entity) {

        UserDto userDto;

        Optional<User> user= Optional.ofNullable(entity.getUser());
        if(user.isPresent()){
            userDto=new UserDto(entity.getUser().getId(),entity.getUser().getEmail(),entity.getUser().getRole().getStringName());
        }else{
            userDto= new UserDto(null,null,null);
        }

        return new CompleteTransportCompanyInformationResource(
            entity.getId(),
            entity.getName(),
            entity.getBusImageUrl(),
                entity.getLogoImageUrl(),
                entity.getDescription(),
                userDto,
                entity.isDeleted()
        );

    }
}
