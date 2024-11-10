package com.dictionary.learning.platform.user;

import com.dictionary.learning.platform.repository.BaseTestRepository;
import java.util.List;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
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
}
