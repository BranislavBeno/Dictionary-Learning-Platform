package com.dictionary.learning.platform.user;

import java.util.Set;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Set<UserDto> findAllUsers() {
        return repository.findAllUsers();
    }
}
