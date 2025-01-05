package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.lesson.Lesson;
import com.dictionary.learning.platform.lesson.LessonService;
import com.dictionary.learning.platform.utils.DictionaryUtils;
import java.util.List;
import org.jspecify.annotations.Nullable;
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

    public void saveWord(Word word) {
        repository.save(word);
    }

    public void addWord(long lessonId, String english, String slovak) {
        Lesson lesson = lessonService.findById(lessonId);
        Word word = createWord(english, slovak);
        word.setLesson(lesson);
        saveWord(word);
    }

    boolean wordExists(long id) {
        return repository.existsById(id);
    }

    @Nullable
    Word findWord(long id) {
        return repository.findById(id).orElse(null);
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
