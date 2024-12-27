package com.dictionary.learning.platform.lesson;

import com.dictionary.learning.platform.utils.DictionaryUtils;
import java.util.Optional;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

@ExtendWith(MockitoExtension.class)
class LessonServiceTest implements WithAssertions {

    private static final int PAGE_SIZE = 5;

    @Mock
    private LessonRepository repository;

    @Mock
    private Page<LessonDto> lessonPage;

    @Mock
    private Lesson lesson;

    private LessonService service;

    @BeforeEach
    void setUp() {
        service = new LessonService(repository, PAGE_SIZE);
    }

    @Test
    void testFindById() {
        // given
        long lessonId = 1;
        Mockito.when(repository.findById(lessonId)).thenReturn(Optional.of(lesson));
        // when
        service.findById(lessonId);
        // then
        Mockito.verify(repository).findById(lessonId);
    }

    @Test
    void testFindByLessonId() {
        // given
        long lessonId = 1;
        LessonDto lessonDto = Mockito.mock(LessonDto.class);
        Mockito.when(repository.findByLessonId(lessonId)).thenReturn(lessonDto);
        // when
        service.findByLessonId(lessonId);
        // then
        Mockito.verify(repository).findByLessonId(lessonId);
    }

    @Test
    void testFindAllByUserNamePaginated() {
        // given
        int page = 1;
        String userName = "jane";
        Mockito.when(repository.findAllByUserNamePaginated(DictionaryUtils.getPageRequest(page, PAGE_SIZE), userName))
                .thenReturn(lessonPage);
        // when
        service.findAllByUserNamePaginated(page, userName);
        // then
        Mockito.verify(repository)
                .findAllByUserNamePaginated(DictionaryUtils.getPageRequest(page, PAGE_SIZE), userName);
    }

    @Test
    void testSaveLesson() {
        // given
        Mockito.when(repository.save(ArgumentMatchers.any(Lesson.class))).thenReturn(lesson);
        // when
        service.saveLesson(lesson);
        // then
        Mockito.verify(repository).save(ArgumentMatchers.any(Lesson.class));
    }

    @Test
    void testLessonExists() {
        // given
        long id = 1;
        Mockito.when(repository.existsById(id)).thenReturn(true);
        // when
        boolean result = service.lessonExists(id);
        // then
        Mockito.verify(repository).existsById(id);
        assertThat(result).isTrue();
    }

    @Test
    void testFindLesson() {
        // given
        long id = 1;
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(lesson));
        // when
        service.findLesson(id);
        // then
        Mockito.verify(repository).findById(id);
    }

    @Test
    void testDeleteLesson() {
        // given
        long id = 1;
        Mockito.doNothing().when(repository).deleteById(id);
        // when
        service.deleteLesson(id);
        // then
        Mockito.verify(repository).deleteById(id);
    }
}
