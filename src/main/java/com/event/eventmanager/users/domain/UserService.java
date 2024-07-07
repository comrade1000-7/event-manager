package com.event.eventmanager.users.domain;

import com.event.eventmanager.users.db.UserRepository;
import com.event.eventmanager.users.db.UserToUserEntityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserToUserEntityMapper mapper;
    public boolean isUserExistsByLogin(String login) {
        return userRepository.findUserEntityByUsername(login).isPresent();
    }

    public User saveUser(User user) {
        log.info("Saving user={}", user.login());
        var userEntity = mapper.UserToUserEntity(user);
        var savedUser = userRepository.save(userEntity);
        return mapper.UserEntityToUser(savedUser);
    }

    public User getUserByLogin(String login) {
        return userRepository.findUserEntityByUsername(login)
                .map(mapper::UserEntityToUser)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User entity not found. Login = %s".formatted(login)
                ));
    }


    public User getUserById(Long id) {
        return userRepository.findUserEntityById(id)
                .map(mapper::UserEntityToUser)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User entity not found. Id = %s".formatted(id)
                ));
    }
}
