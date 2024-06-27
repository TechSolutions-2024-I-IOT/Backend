package com.chapaTuBus.webService.userAccount.interfaces.rest.transform;

import com.chapaTuBus.webService.userAccount.domain.model.commands.auth.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.domain.model.entities.Role;
import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.Roles;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.RoleRepository;
import com.chapaTuBus.webService.userAccount.interfaces.rest.resources.RegisterUserResource;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserCommandFromResourceAssembler {

    private final RoleRepository roleRepository;

    public RegisterUserCommandFromResourceAssembler(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RegisterUserCommand toCommandFromResource(RegisterUserResource registerUserResource) {
        Role role = getRoleFromResource(registerUserResource);
        return new RegisterUserCommand(registerUserResource.email(), registerUserResource.password(), role);
    }

    private Role getRoleFromResource(RegisterUserResource registerUserResource) {
        try {
            Roles roleType = Roles.valueOf(registerUserResource.role());
            // Busca el rol en la base de datos por su tipo
            return roleRepository.findByType(roleType)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
        } catch (IllegalArgumentException | NullPointerException e) {
            // Si el rol es inválido o nulo, asigna el rol por defecto
            return roleRepository.findByType(Roles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
        }
    }
}