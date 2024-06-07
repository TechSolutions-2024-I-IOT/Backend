package com.chapaTuBus.webService.userAccount.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain model representation of the Profile for a new User
 * Contains the profile information of the user
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String first_name;
    private String last_name;
    private String photo_url;

    public Profile(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

}
