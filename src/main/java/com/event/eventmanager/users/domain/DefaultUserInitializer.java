package com.event.eventmanager.users.domain;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefaultUserInitializer {

    private UserService userService;
    private PasswordEncoder encoder;

    @PostConstruct
    public void initUsers() {
        createUserIsNotExist("admin", "admin", Role.ADMIN);
        createUserIsNotExist("user", "user", Role.USER);
    }

    private void createUserIsNotExist(
            String login,
            String password,
            Role role
    ) {
        if (userService.isUserExistsByLogin(login)) return;

        var encodedPass = encoder.encode(password);
        var user = new User(
                null,
                login,
                18,
                encodedPass,
                role
        );
        userService.saveUser(user);
    }
}
