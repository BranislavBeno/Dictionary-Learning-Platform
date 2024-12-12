package com.dictionary.learning.platform.user;

import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    void findByUsername() {
        // given
        String userName = "jane";
        var user = Mockito.mock(User.class);
        Mockito.when(repository.findByUsername(userName)).thenReturn(Optional.of(user));
        // when
        service.findByUsername(userName);
        // then
        Mockito.verify(repository).findByUsername(userName);
    }

    @Test
    void testFindAllByUserId() {
        // given
        var user = Mockito.mock(UserDto.class);
        Mockito.when(repository.findAllUsers()).thenReturn(Set.of(user));
        // when
        service.findAllUsers();
        // then
        Mockito.verify(repository).findAllUsers();
    }
}
