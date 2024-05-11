package com.chapaTuBus.webService.userAccount.domain.model.commands;

import com.chapaTuBus.webService.userAccount.domain.model.entities.Role;

import java.util.List;

public record RegisterUserCommand(String email, String password, List<Role> roles){

}
