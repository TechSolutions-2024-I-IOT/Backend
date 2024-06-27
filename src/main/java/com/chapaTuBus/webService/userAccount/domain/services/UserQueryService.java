package com.chapaTuBus.webService.userAccount.domain.services;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.domain.model.queries.GetCompleteUserProfileByUserIdQuery;

import java.util.Optional;

public interface UserQueryService {
    Optional<User> handle(GetCompleteUserProfileByUserIdQuery query);
}
