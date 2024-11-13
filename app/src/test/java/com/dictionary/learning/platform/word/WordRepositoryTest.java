package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.repository.BaseTestRepository;
import java.util.Set;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "/sql/init_db.sql")
class WordRepositoryTest extends BaseTestRepository implements WithAssertions {

    @Autowired
    private WordRepository repository;

    @ParameterizedTest
    @CsvSource({"0,5", "1,4"})
    void testFindAll(int pageNumber, int expected) {
        Page<Word> words = repository.findAll(PageRequest.of(pageNumber, 5));
        assertThat(words).hasSize(expected);
    }

    @ParameterizedTest
    @CsvSource({"0,5", "1,2"})
    void testFindAllByUserId(int pageNumber, int expected) {
        Page<WordDto> words = repository.findAllByUserId(PageRequest.of(pageNumber, 5), 1);
        assertThat(words).hasSize(expected);
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

    @Test
    void testFindAllByUserIdByGradeBetweenLessons() {
        Set<WordDto> words = repository.findAllByUserIdByGradeBetweenLessons(1, 2, 1, 2);

        assertThat(words).hasSize(3);
    }
}
