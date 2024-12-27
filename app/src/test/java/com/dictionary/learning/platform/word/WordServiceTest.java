package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.utils.DictionaryUtils;
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

@ExtendWith(MockitoExtension.class)
class WordServiceTest implements WithAssertions {

    private static final int PAGE_SIZE = 5;

    @Mock
    private WordRepository repository;

    @Mock
    private Page<WordDto> wordPage;

    @Mock
    private Word word;

    private WordService service;

    @BeforeEach
    void setUp() {
        service = new WordService(repository, PAGE_SIZE);
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
    void testSaveWord() {
        // given
        Mockito.when(repository.save(ArgumentMatchers.any(Word.class))).thenReturn(word);
        // when
        service.saveWord(word);
        // then
        Mockito.verify(repository).save(ArgumentMatchers.any(Word.class));
    }

    @Test
    void testWordExists() {
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
    void testFindWord() {
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
    void testCompare(String translation, boolean toEn, String expected) {
        var checked = new WordDto(1L, "Mother", "Matka");
        String result = service.compareTranslation(checked, translation, toEn);
        assertThat(result).isEqualTo(expected);
    }
}
