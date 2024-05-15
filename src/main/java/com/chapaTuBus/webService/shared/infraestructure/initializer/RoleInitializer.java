package com.chapaTuBus.webService.shared.infraestructure.initializer;

import com.chapaTuBus.webService.userAccount.domain.model.entities.Role;
import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.Roles;
import com.chapaTuBus.webService.userAccount.domain.services.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RoleInitializer implements CommandLineRunner {

    private final RoleService roleService;

    public RoleInitializer(RoleService roleService){
        this.roleService=roleService;
    }

    /**
     *
     * Database role intializer , store in the db
     * all the predefined roles existed
     */

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(Roles.values())
                .forEach(roleType -> {
                    if(!roleService.existByType(roleType)){
                        Role role=new Role(roleType);
                        roleService.save(role);
                    }
                });

    }
}
