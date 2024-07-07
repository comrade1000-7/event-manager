package com.event.eventmanager.users.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByUsername (String username);

    Optional<UserEntity> findUserEntityById(Long id);
}
