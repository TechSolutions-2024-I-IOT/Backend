package com.chapaTuBus.webService.userAccount.domain.model.commands.users;

public record ModifyProfileCommand (
        Long userId,
        String firstName,
        String lastName,
        String photoUrl
) {
}
