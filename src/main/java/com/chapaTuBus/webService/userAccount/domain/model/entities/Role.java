package com.chapaTuBus.webService.userAccount.domain.model.entities;

import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Role entity
 *
 * Represents the role of a user in the system
 * It´s used to define the permissions of a user
 */

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Roles type;

    public Role(Roles type) {this.type=type;}

    public String getStringName(){return type.name();}

    public static Role getDefaultRole(){
        return new Role(Roles.ROLE_USER);
    }

    public static Role toRoleFromName(String name){
        return new Role(Roles.valueOf(name));
    }


}
