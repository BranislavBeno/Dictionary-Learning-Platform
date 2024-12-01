package com.dictionary.learning.platform.word;

import java.util.Set;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public final class WordService {

    private final WordRepository repository;
    private final int pageSize;

    public WordService(WordRepository repository, int pageSize) {
        this.repository = repository;
        this.pageSize = pageSize;
    }

    Page<WordDto> findAllByUserId(int page, long userId) {
        return repository.findAllByUserId(getPageRequest(page), userId);
    }

    Set<WordDto> findAllByUserIdByGrade(long userId, int grade) {
        return repository.findAllByUserIdByGrade(userId, grade);
    }

    Set<WordDto> findAllByUserNameByGradeByLesson(String userName, int grade, int lesson) {
        return repository.findAllByUserNameByGradeByLesson(userName, grade, lesson);
    }

    Set<WordDto> findAllByUserIdByGradeBetweenLessons(long userId, int grade, int firstLesson, int lastLesson) {
        return repository.findAllByUserIdByGradeBetweenLessons(userId, grade, firstLesson, lastLesson);
    }

    Word saveWord(Word word) {
        return repository.save(word);
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

    String compareTranslation(WordDto word, String translation, boolean toEn) {
        String original = toEn ? word.en() : word.sk();

        return original.equalsIgnoreCase(translation) ? null : original;
    }

    private PageRequest getPageRequest(int page) {
        return PageRequest.of(page, pageSize);
    }
}
