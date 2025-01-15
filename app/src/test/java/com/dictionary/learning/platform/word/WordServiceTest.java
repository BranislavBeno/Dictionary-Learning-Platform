package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.lesson.Lesson;
import com.dictionary.learning.platform.lesson.LessonService;
import com.dictionary.learning.platform.utils.DictionaryUtils;
import java.util.List;
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
class WordServiceTest implements WithAssertions {

    private static final int PAGE_SIZE = 5;

    @Mock
    private LessonService lessonService;

    @Mock
    private WordRepository repository;

    @Mock
    private Page<WordDto> wordPage;

    @Mock
    private Word word;

    private WordService service;

    @BeforeEach
    void setUp() {
        service = new WordService(repository, lessonService, PAGE_SIZE);
    }

    @Test
    void testFindAllByLessonIdPaginated() {
        // given
        int page = 1;
        long lessonId = 1;
        Mockito.when(repository.findAllByLessonIdPaginated(DictionaryUtils.getPageRequest(page, PAGE_SIZE), lessonId))
                .thenReturn(wordPage);
        // when
        service.findAllLessonIdPaginated(page, lessonId);
        // then
        Mockito.verify(repository)
                .findAllByLessonIdPaginated(DictionaryUtils.getPageRequest(page, PAGE_SIZE), lessonId);
    }

    @Test
    void testFindAllByLessonId() {
        // given
        int lessonId = 1;
        WordDto wordDto = Mockito.mock(WordDto.class);
        Mockito.when(repository.findAllByLessonId(lessonId)).thenReturn(List.of(wordDto));
        // when
        service.findAllByLessonId(lessonId);
        // then
        Mockito.verify(repository).findAllByLessonId(lessonId);
    }

    @Test
    void testFindByWordId() {
        // given
        long wordId = 1;
        WordDto wordDto = Mockito.mock(WordDto.class);
        Mockito.when(repository.findByWordId(wordId)).thenReturn(wordDto);
        // when
        service.findByWordId(wordId);
        // then
        Mockito.verify(repository).findByWordId(wordId);
    }

    @Test
    void testSaveWord() {
        // given
        long wordId = 2;
        long lessonId = 1;
        var lesson = Mockito.mock(Lesson.class);
        Mockito.when(repository.findById(wordId)).thenReturn(Optional.of(word));
        Mockito.when(lessonService.findById(lessonId)).thenReturn(Optional.of(lesson));
        Mockito.when(repository.save(word)).thenReturn(word);
        // when
        service.saveWord(lessonId, wordId, "en", "sk");
        // then
        Mockito.verify(repository).findById(wordId);
        Mockito.verify(lessonService).findById(lessonId);
        Mockito.verify(repository).save(word);
    }

    @Test
    void testDeleteWord() {
        // given
        long id = 1;
        Mockito.doNothing().when(repository).deleteById(id);
        // when
        service.deleteWord(id);
        // then
        Mockito.verify(repository).deleteById(id);
    }
}
