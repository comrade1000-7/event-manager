package com.event.eventmanager.users.db;

import com.event.eventmanager.users.domain.Role;
import com.event.eventmanager.users.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserEntityMapper {

    public UserEntity UserToUserEntity(User user) {
        return new UserEntity(
                user.id(),
                user.login(),
                user.passwordHash(),
                user.age(),
                user.role().name()
        );
    }

    public User UserEntityToUser(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getAge(),
                userEntity.getPassword(),
                Role.valueOf(userEntity.getRole())
        );
    }
}
