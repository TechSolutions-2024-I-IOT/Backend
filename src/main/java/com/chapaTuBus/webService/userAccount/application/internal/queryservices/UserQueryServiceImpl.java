package com.chapaTuBus.webService.userAccount.application.internal.queryservices;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.domain.model.queries.GetCompleteUserProfileByUserIdQuery;
import com.chapaTuBus.webService.userAccount.domain.services.UserQueryService;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetCompleteUserProfileByUserIdQuery query) {
        return userRepository.findById(query.userId());
    }
}
