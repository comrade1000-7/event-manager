package com.event.eventmanager.users.db;

import com.event.eventmanager.users.domain.Role;
import com.event.eventmanager.users.api.SignupRequest;
import com.event.eventmanager.users.domain.User;
import com.event.eventmanager.users.domain.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserRegistrationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public User authorizeUser(SignupRequest signupRequest) {
        if (userService.isUserExistsByLogin(signupRequest.login())) {
            throw new IllegalArgumentException("User with defined login already exist");
        }
        var encodedPass = passwordEncoder.encode(signupRequest.password());
        var user = new User(
                null,
                signupRequest.login(),
                signupRequest.age(),
                encodedPass,
                Role.USER
        );
        return userService.saveUser(user);
    }
}
