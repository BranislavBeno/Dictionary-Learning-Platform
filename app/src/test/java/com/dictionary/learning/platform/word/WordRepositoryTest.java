package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.repository.BaseTestRepository;
import java.util.List;
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

    @Test
    void testFindByWordId() {
        WordDto dto = repository.findByWordId(4L);
        assertThat(dto.en()).isEqualTo("brother");
        assertThat(dto.sk()).isEqualTo("brat");
    }

    @ParameterizedTest
    @CsvSource({"0,5", "1,4"})
    void testFindAll(int pageNumber, int expected) {
        Page<Word> words = repository.findAll(PageRequest.of(pageNumber, 5));
        assertThat(words).hasSize(expected);
    }

    @ParameterizedTest
    @CsvSource({"0,3", "1,1"})
    void testFindAllByLessonIdPaginated(int pageNumber, int expected) {
        Page<WordDto> words = repository.findAllByLessonIdPaginated(PageRequest.of(pageNumber, 3), 1);
        assertThat(words).hasSize(expected);
    }

    @Test
    void testFindAllByLessonId() {
        List<WordDto> words = repository.findAllByLessonId(3);
        assertThat(words).hasSize(1);
    }
}
