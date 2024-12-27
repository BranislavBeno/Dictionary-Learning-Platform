package com.dictionary.learning.platform.lesson;

import com.dictionary.learning.platform.utils.DictionaryUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;

public class LessonService {

    private final LessonRepository repository;
    private final int pageSize;

    public LessonService(LessonRepository repository, int pageSize) {
        this.repository = repository;
        this.pageSize = pageSize;
    }

    public Lesson findById(long lessonId) {
        return repository.findById(lessonId).orElseThrow(LessonNotFound::new);
    }

    LessonDto findByLessonId(long lessonId) {
        return repository.findByLessonId(lessonId);
    }

    Page<LessonDto> findAllByUserNamePaginated(int page, String userName) {
        return repository.findAllByUserNamePaginated(DictionaryUtils.getPageRequest(page, pageSize), userName);
    }

    Lesson saveLesson(Lesson lesson) {
        return repository.save(lesson);
    }

    boolean lessonExists(long id) {
        return repository.existsById(id);
    }

    @Nullable
    Lesson findLesson(long id) {
        return repository.findById(id).orElse(null);
    }

    void deleteLesson(long id) {
        repository.deleteById(id);
    }
}