package com.event.eventmanager.users.domain;

import com.event.eventmanager.security.JwtTokenManager;
import com.event.eventmanager.users.api.SignInRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final JwtTokenManager jwtTokenManager;
    private final UserService userService;
    private final PasswordEncoder encoder;

    public String authenticateUser(SignInRequest request) {
        if (!userService.isUserExistsByLogin(request.login()))
            throw new BadCredentialsException("Bad credentials");

        var user = userService.getUserByLogin(request.login());

        if (!encoder.matches(request.password(), user.passwordHash()))
            throw new BadCredentialsException("Bad credentials");

        return jwtTokenManager.generateToken(user);
    }
}
