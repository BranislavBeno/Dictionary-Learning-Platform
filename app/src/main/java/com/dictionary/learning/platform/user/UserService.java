package com.dictionary.learning.platform.user;

import java.util.Set;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Set<UserDto> findAllUsers() {
        return repository.findAllUsers();
    }
}
