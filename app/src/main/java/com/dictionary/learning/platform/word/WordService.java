package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.lesson.LessonService;
import com.dictionary.learning.platform.utils.DictionaryUtils;
import java.util.List;
import org.springframework.data.domain.Page;

public final class WordService {

    private final WordRepository repository;
    private final LessonService lessonService;
    private final int pageSize;

    public WordService(WordRepository repository, LessonService lessonService, int pageSize) {
        this.repository = repository;
        this.lessonService = lessonService;
        this.pageSize = pageSize;
    }

    public Page<WordDto> findAllLessonIdPaginated(int page, long lessonId) {
        return repository.findAllByLessonIdPaginated(DictionaryUtils.getPageRequest(page, pageSize), lessonId);
    }

    public List<WordDto> findAllByLessonId(long lessonId) {
        return repository.findAllByLessonId(lessonId);
    }

    public WordDto findByWordId(long id) {
        return repository.findByWordId(id);
    }

    public void saveWord(long lessonId, long wordId, String english, String slovak) {
        Word word = repository.findById(wordId).orElse(new Word());

        lessonService.findById(lessonId).ifPresent(word::setLesson);
        word.setEn(english);
        word.setSk(slovak);

        repository.save(word);
    }

    public void deleteWord(long id) {
        repository.deleteById(id);
    }
}
