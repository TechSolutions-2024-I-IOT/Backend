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
@Embeddable
public class Profile {

    private String first_name;
    private String last_name;
    private String photo_url;


}
