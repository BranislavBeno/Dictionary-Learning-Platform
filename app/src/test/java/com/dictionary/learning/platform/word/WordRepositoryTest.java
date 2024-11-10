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

        assertThat(words).hasSize(9);
    }

    @Test
    void testFindAllByUserId() {
        Set<WordDto> words = repository.findAllByUserId(1);

        assertThat(words).hasSize(7);
    }

    @Test
    void testFindAllByUserIdByGrade() {
        Set<WordDto> words = repository.findAllByUserIdByGrade(1, 2);

        assertThat(words).hasSize(3);
    }

    @Test
    void testFindAllByUserIdByGradeByLesson() {
        Set<WordDto> words = repository.findAllByUserIdByGradeByLesson(1, 2, 2);

        assertThat(words).hasSize(1);
    }
}
