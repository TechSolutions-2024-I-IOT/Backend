package com.chapaTuBus.webService.userAccount.interfaces.rest.resources;

public record ModifyProfileResource(
        String firstName,
        String lastName,
        String photoUrl
) {
}
