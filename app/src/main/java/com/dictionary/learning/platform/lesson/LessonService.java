package com.dictionary.learning.platform.lesson;

import com.dictionary.learning.platform.utils.DictionaryUtils;
import java.util.Optional;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;

public class LessonService {

    private final LessonRepository repository;
    private final int pageSize;

    public LessonService(LessonRepository repository, int pageSize) {
        this.repository = repository;
        this.pageSize = pageSize;
    }

    public Optional<Lesson> findById(long lessonId) {
        return repository.findById(lessonId);
    }

    public LessonDto findByLessonId(long lessonId) {
        return repository.findByLessonId(lessonId);
    }

    public Page<LessonDto> findAllByUserNamePaginated(int page, String userName) {
        return repository.findAllByUserNamePaginated(DictionaryUtils.getPageRequest(page, pageSize), userName);
    }

    public void updateLessonRate(long lessonId, double rate) {
        repository
                .findById(lessonId)
                .ifPresentOrElse(
                        l -> {
                            double previousRate = l.getSuccessRate() != null ? l.getSuccessRate() : 0;
                            double newRate = DictionaryUtils.computeAverage(rate, previousRate);
                            l.setSuccessRate(newRate);
                            repository.save(l);
                        },
                        LessonNotFound::new);
    }

    public void deleteLesson(long id) {
        repository.deleteById(id);
    }

    public void updateLesson(long lessonId, String lesson, int grade) {
        repository
                .findById(lessonId)
                .ifPresentOrElse(
                        l -> {
                            l.setTitle(lesson);
                            l.setGrade(grade);
                            repository.save(l);
                        },
                        LessonNotFound::new);
    }

    boolean lessonExists(long id) {
        return repository.existsById(id);
    }

    @Nullable
    Lesson findLesson(long id) {
        return repository.findById(id).orElse(null);
    }
}
