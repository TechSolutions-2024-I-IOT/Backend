package com.chapaTuBus.webService.userAccount.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Domain model representation of the Profile for a new User
 * Contains the profile information of the user
 */

@NoArgsConstructor
@AllArgsConstructor
@Entity

@Embeddable
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;
    private String photo_url;


}
