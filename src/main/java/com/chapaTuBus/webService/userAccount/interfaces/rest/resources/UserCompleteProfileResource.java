package com.chapaTuBus.webService.userAccount.interfaces.rest.resources;

public record UserCompleteProfileResource
        (
                Long id,
                String email,
                String firstName,
                String lastName,
                String photoUrl,
                String role
         )
{
}
