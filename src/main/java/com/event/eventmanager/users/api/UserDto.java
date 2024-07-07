package com.event.eventmanager.users.api;

import com.event.eventmanager.users.domain.Role;

public record UserDto(
        Long id,
        String login,
        Integer age,
        Role role
) {
}
