package com.dictionary.learning.platform.word;

import java.util.List;
import java.util.Optional;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class WordServiceTest implements WithAssertions {

    @Mock
    private WordRepository repository;

    @Mock
    private Page<WordDto> wordPage;

    @Mock
    private WordDto wordDto;

    @Mock
    private Word word;

    private WordService service;

    @BeforeEach
    void setUp() {
        service = new WordService(repository, 5);
    }

    @Test
    void testFindAllByUserNameByGradeByLessonPaginated() {
        // given
        int page = 1;
        String userName = "jane";
        int grade = 1;
        int lesson = 1;
        Mockito.when(repository.findAllByUserNameByGradeByLessonPaginated(
                        getPageRequest(page), userName, grade, lesson))
                .thenReturn(wordPage);
        // when
        service.findAllByUserNameByGradeByLessonPaginated(page, userName, grade, lesson);
        // then
        Mockito.verify(repository)
                .findAllByUserNameByGradeByLessonPaginated(getPageRequest(page), userName, grade, page);
    }

    @Test
    void testFindAllByUserNameByGradeByLesson() {
        // given
        String userName = "bob";
        int grade = 1;
        int lesson = 1;
        Mockito.when(repository.findAllByUserNameByGradeByLesson(userName, grade, lesson))
                .thenReturn(List.of(wordDto));
        // when
        service.findAllByUserNameByGradeByLesson(userName, grade, lesson);
        // then
        Mockito.verify(repository).findAllByUserNameByGradeByLesson(userName, grade, lesson);
    }

    @Test
    void testFindAllByUserIdByGradeBetweenLessons() {
        // given
        long userId = 1;
        int grade = 1;
        int firstLesson = 1;
        int lastLesson = 2;
        Mockito.when(repository.findAllByUserIdByGradeBetweenLessons(userId, grade, firstLesson, lastLesson))
                .thenReturn(List.of(wordDto));
        // when
        service.findAllByUserIdByGradeBetweenLessons(userId, grade, firstLesson, lastLesson);
        // then
        Mockito.verify(repository).findAllByUserIdByGradeBetweenLessons(userId, grade, firstLesson, lastLesson);
    }

    @Test
    void saveWord() {
        // given
        Mockito.when(repository.save(ArgumentMatchers.any(Word.class))).thenReturn(word);
        // when
        service.saveWord(word);
        // then
        Mockito.verify(repository).save(ArgumentMatchers.any(Word.class));
    }

    @Test
    void wordExists() {
        // given
        long id = 1;
        Mockito.when(repository.existsById(id)).thenReturn(true);
        // when
        boolean result = service.wordExists(id);
        // then
        Mockito.verify(repository).existsById(id);
        assertThat(result).isTrue();
    }

    @Test
    void findWord() {
        // given
        long id = 1;
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(word));
        // when
        service.findWord(id);
        // then
        Mockito.verify(repository).findById(id);
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

    @ParameterizedTest
    @CsvSource({"Matka,false,", "matka,false,", "mama,false,Matka", "Mother,true,", "mother,true,", "mom,true,Mother"})
    void compare(String translation, boolean toEn, String expected) {
        var checked = new WordDto(1L, "Mother", "Matka", 1, 1);
        String result = service.compareTranslation(checked, translation, toEn);
        assertThat(result).isEqualTo(expected);
    }

    private PageRequest getPageRequest(int page) {
        return PageRequest.of(page, 5);
    }
}
