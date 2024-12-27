package com.dictionary.learning.platform.lesson;

import com.dictionary.learning.platform.repository.BaseTestRepository;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "/sql/init_db.sql")
class LessonRepositoryTest extends BaseTestRepository implements WithAssertions {

    @Autowired
    private LessonRepository repository;

    @Test
    void testFindAll() {
        List<Lesson> lessons = repository.findAll();
        assertThat(lessons).hasSize(4);
    }

    @Test
    void testFindById() {
        Optional<Lesson> lesson = repository.findById(1L);
        assertThat(lesson).isNotNull().hasValueSatisfying(l -> assertThat(l.getNumber())
                .isEqualTo(1));
    }

    @Test
    void testFindByLessonId() {
        LessonDto lesson = repository.findByLessonId(4L);
        assertThat(lesson.number()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource({"0,2", "1,1"})
    void testFindAllByUserNamePaginated(int pageNumber, int expected) {
        Page<LessonDto> lessons = repository.findAllByUserNamePaginated(PageRequest.of(pageNumber, 2), "jane");
        assertThat(lessons).hasSize(expected);
    }
}
