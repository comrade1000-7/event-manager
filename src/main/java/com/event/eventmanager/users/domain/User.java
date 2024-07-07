package com.event.eventmanager.users.domain;

public record User(
        Long id,
        String login,
        Integer age,
        String passwordHash,
        Role role
) {
}
