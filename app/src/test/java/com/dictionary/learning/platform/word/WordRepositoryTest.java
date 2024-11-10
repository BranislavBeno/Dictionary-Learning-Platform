package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.repository.BaseTestRepository;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "/sql/init_db.sql")
class WordRepositoryTest extends BaseTestRepository implements WithAssertions {

    @Autowired
    private WordRepository repository;

    @Test
    void testFindAll() {
        List<Word> words = repository.findAll();

        assertThat(words).hasSize(5);
    }

    @Test
    void testFindAllByUserId() {
        Set<Word> words = repository.findAllByUserId(1);

        assertThat(words).hasSize(3);
    }
}
