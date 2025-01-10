package com.dictionary.learning.platform.lesson;

import com.dictionary.learning.platform.user.User;
import com.dictionary.learning.platform.user.UserRepository;
import com.dictionary.learning.platform.utils.DictionaryUtils;
import java.util.Optional;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

@ExtendWith(MockitoExtension.class)
class LessonServiceTest implements WithAssertions {

    private static final int PAGE_SIZE = 5;

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Page<LessonDto> lessonPage;

    @Mock
    private Lesson lesson;

    private LessonService service;

    @BeforeEach
    void setUp() {
        service = new LessonService(lessonRepository, userRepository, PAGE_SIZE);
    }

    @Test
    void testFindById() {
        // given
        long lessonId = 1;
        Mockito.when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));
        // when
        service.findById(lessonId);
        // then
        Mockito.verify(lessonRepository).findById(lessonId);
    }

    @Test
    void testFindByLessonId() {
        // given
        long lessonId = 1;
        LessonDto lessonDto = Mockito.mock(LessonDto.class);
        Mockito.when(lessonRepository.findByLessonId(lessonId)).thenReturn(lessonDto);
        // when
        service.findByLessonId(lessonId);
        // then
        Mockito.verify(lessonRepository).findByLessonId(lessonId);
    }

    @Test
    void testFindAllByUserNamePaginated() {
        // given
        int page = 1;
        String userName = "jane";
        Mockito.when(lessonRepository.findAllByUserNamePaginated(
                        DictionaryUtils.getPageRequest(page, PAGE_SIZE), userName))
                .thenReturn(lessonPage);
        // when
        service.findAllByUserNamePaginated(page, userName);
        // then
        Mockito.verify(lessonRepository)
                .findAllByUserNamePaginated(DictionaryUtils.getPageRequest(page, PAGE_SIZE), userName);
    }

    @Test
    void testFindAllPaginated() {
        // given
        int page = 1;
        Mockito.when(lessonRepository.findAllPaginated(DictionaryUtils.getPageRequest(page, PAGE_SIZE)))
                .thenReturn(lessonPage);
        // when
        service.findAllPaginated(page);
        // then
        Mockito.verify(lessonRepository).findAllPaginated(DictionaryUtils.getPageRequest(page, PAGE_SIZE));
    }

    @Test
    void testUpdateLessonRate() {
        // given
        long lessonId = 1;
        double rate = 0.755;
        Mockito.when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));
        Mockito.doNothing().when(lesson).setSuccessRate(rate);
        Mockito.when(lessonRepository.save(lesson)).thenReturn(lesson);
        // when
        service.updateLessonRate(lessonId, rate);
        // then
        Mockito.verify(lessonRepository).findById(lessonId);
        Mockito.verify(lesson).setSuccessRate(rate);
        Mockito.verify(lessonRepository).save(lesson);
    }

    @Test
    void testUpdateLesson() {
        // given
        long lessonId = 1;
        String userName = "jane";
        var user = Mockito.mock(User.class);
        Mockito.when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));
        Mockito.when(userRepository.findByUsername(userName)).thenReturn(Optional.of(user));
        Mockito.when(lessonRepository.save(lesson)).thenReturn(lesson);
        // when
        service.updateLesson(lessonId, "lesson", 1, userName);
        // then
        Mockito.verify(lessonRepository).findById(lessonId);
        Mockito.verify(userRepository).findByUsername(userName);
        Mockito.verify(lessonRepository).save(lesson);
    }

    @Test
    void testLessonExists() {
        // given
        long id = 1;
        Mockito.when(lessonRepository.existsById(id)).thenReturn(true);
        // when
        boolean result = service.lessonExists(id);
        // then
        Mockito.verify(lessonRepository).existsById(id);
        assertThat(result).isTrue();
    }

    @Test
    void testFindLesson() {
        // given
        long id = 1;
        Mockito.when(lessonRepository.findById(id)).thenReturn(Optional.of(lesson));
        // when
        service.findLesson(id);
        // then
        Mockito.verify(lessonRepository).findById(id);
    }

    @Test
    void testDeleteLesson() {
        // given
        long id = 1;
        Mockito.doNothing().when(lessonRepository).deleteById(id);
        // when
        service.deleteLesson(id);
        // then
        Mockito.verify(lessonRepository).deleteById(id);
    }
}
