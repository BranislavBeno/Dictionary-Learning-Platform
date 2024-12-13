package com.dictionary.learning.platform.user;

import com.dictionary.learning.platform.repository.BaseTestRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "/sql/init_db.sql")
class UserRepositoryTest extends BaseTestRepository implements WithAssertions {

    @Autowired
    private UserRepository repository;

    @Test
    void testFindAll() {
        List<User> users = repository.findAll();
        assertThat(users).hasSize(2);
    }

    @Test
    void testFindAllUsers() {
        Set<UserDto> users = repository.findAllUsers();
        assertThat(users).hasSize(2);
    }

    @ParameterizedTest
    @CsvSource({"jane,ROLE_ADMIN", "bob,ROLE_USER"})
    void findByUsername(String username, String role) {
        Optional<User> user = repository.findByUsername(username);
        assertThat(user).hasValueSatisfying(u -> assertThat(u.getRole()).isEqualTo(role));
    }
}
