package com.chapaTuBus.webService.userAccount.interfaces.rest;

import com.chapaTuBus.webService.userAccount.domain.model.queries.GetCompleteUserProfileByUserIdQuery;
import com.chapaTuBus.webService.userAccount.domain.services.UserQueryService;
import com.chapaTuBus.webService.userAccount.interfaces.rest.resources.UserCompleteProfileResource;
import com.chapaTuBus.webService.userAccount.interfaces.rest.transform.UserCompleteProfileResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserQueryService userQueryService;

    public UserController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }


    @GetMapping("/user/profile")
    public ResponseEntity<UserCompleteProfileResource> getUserProfileByUserId(@RequestParam("userId") Long userId) {

        var getUserProfileByUserId = new GetCompleteUserProfileByUserIdQuery(userId);

        var user = userQueryService.handle(getUserProfileByUserId);

        var userCompleteProfile = user.map(UserCompleteProfileResourceFromEntityAssembler::toResourceFromEntity);

        return userCompleteProfile.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());


    }


}
