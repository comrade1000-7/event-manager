package com.event.eventmanager.users.api;

import com.event.eventmanager.security.JwtTokenManager;
import com.event.eventmanager.users.domain.AuthenticationService;
import com.event.eventmanager.users.domain.User;
import com.event.eventmanager.users.domain.UserService;
import com.event.eventmanager.users.db.UserRegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserRegistrationService userRegistrationService;
    private final UserService userService;
    private final JwtTokenManager jwtTokenManager;
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<UserDto> authorizeUser(
            @RequestBody @Valid SignupRequest signupRequest
    ) {
        log.info("Get request for authorize: login={}", signupRequest.login());
        var user = userRegistrationService.authorizeUser(signupRequest);
        var token = jwtTokenManager.generateToken(user);
        log.info("token = {}", token);
        log.info("valid = {}", jwtTokenManager.isTokenValid(token));
        log.info("login = {}", jwtTokenManager.getLoginFromToken(token));
        return ResponseEntity.status(201)
                .body(userEntityToUserDto(user));
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtTokenResponse> authenticate (
            @RequestBody @Valid SignInRequest signInRequest) {
        log.info("Get request for authenticate user: login = {}", signInRequest.login());
        var token = authenticationService.authenticateUser(signInRequest);
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserInfo(
            @PathVariable Long userId
    ) {
        log.info("Get request for user id {}", userId);
        var userEntity = userService.getUserById(userId);
        return ResponseEntity.ok(userEntityToUserDto(userEntity));
    }

    private UserDto userEntityToUserDto(User user) {
        return new UserDto(
                user.id(),
                user.login(),
                user.age(),
                user.role()
        );
    }
}
