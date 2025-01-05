package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.lesson.Lesson;
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

    public void addWord(long lessonId, String english, String slovak) {
        Lesson lesson = lessonService.findById(lessonId);
        Word word = createWord(english, slovak);
        word.setLesson(lesson);
        repository.save(word);
    }

    public void updateWord(long wordId, String english, String slovak) {
        repository.findById(wordId).ifPresent(w -> {
            w.setEn(english);
            w.setSk(slovak);
            repository.save(w);
        });
    }

    boolean wordExists(long id) {
        return repository.existsById(id);
    }

    void deleteWord(long id) {
        repository.deleteById(id);
    }

    private Word createWord(String english, String slovak) {
        Word word = new Word();
        word.setEn(english);
        word.setSk(slovak);

        return word;
    }
}
