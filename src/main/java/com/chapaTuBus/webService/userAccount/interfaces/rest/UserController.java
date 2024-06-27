package com.chapaTuBus.webService.userAccount.interfaces.rest;

import com.chapaTuBus.webService.userAccount.domain.model.queries.GetCompleteUserProfileByUserIdQuery;
import com.chapaTuBus.webService.userAccount.domain.services.UserCommandService;
import com.chapaTuBus.webService.userAccount.domain.services.UserQueryService;
import com.chapaTuBus.webService.userAccount.interfaces.rest.resources.ModifyProfileResource;
import com.chapaTuBus.webService.userAccount.interfaces.rest.resources.UserCompleteProfileResource;
import com.chapaTuBus.webService.userAccount.interfaces.rest.transform.ModifyProfileCommandFromResourceAssembler;
import com.chapaTuBus.webService.userAccount.interfaces.rest.transform.UserCompleteProfileResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService,UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
        this.userCommandService=userCommandService;
    }


    @GetMapping("/user/profile")
    public ResponseEntity<UserCompleteProfileResource> getUserProfileByUserId(@RequestParam("userId") Long userId) {

        var getUserProfileByUserId = new GetCompleteUserProfileByUserIdQuery(userId);

        var user = userQueryService.handle(getUserProfileByUserId);

        var userCompleteProfile = user.map(UserCompleteProfileResourceFromEntityAssembler::toResourceFromEntity);

        return userCompleteProfile.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());


    }

    @PutMapping("/user/profile")
    public ResponseEntity<UserCompleteProfileResource> modifyProfileByUserId(@RequestParam("userId") Long userId, @RequestBody ModifyProfileResource modifyProfileResource){

        var modifyProfileCommand = ModifyProfileCommandFromResourceAssembler.toCommand(userId, modifyProfileResource);
        var user = userCommandService.handle(modifyProfileCommand);

        return user.map(updatedUser ->
                        ResponseEntity.ok(UserCompleteProfileResourceFromEntityAssembler.toResourceFromEntity(updatedUser)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
