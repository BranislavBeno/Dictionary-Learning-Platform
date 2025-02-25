package com.dictionary.learning.platform.lesson;

import com.dictionary.learning.platform.user.UserRepository;
import com.dictionary.learning.platform.utils.DictionaryUtils;
import java.util.Optional;
import org.springframework.data.domain.Page;

public class LessonService {

    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final int pageSize;

    public LessonService(LessonRepository lessonRepository, UserRepository userRepository, int pageSize) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.pageSize = pageSize;
    }

    public Optional<Lesson> findById(long lessonId) {
        return lessonRepository.findById(lessonId);
    }

    public LessonDto findByLessonId(long lessonId) {
        return lessonRepository.findByLessonId(lessonId);
    }

    public Page<LessonDto> findAllByUserNamePaginated(int page, String userName) {
        return lessonRepository.findAllByUserNamePaginated(DictionaryUtils.getPageRequest(page, pageSize), userName);
    }

    public Page<LessonDto> findAllPaginated(int page) {
        return lessonRepository.findAllPaginated(DictionaryUtils.getPageRequest(page, pageSize));
    }

    public void updateLessonRate(long lessonId, double rate) {
        lessonRepository
                .findById(lessonId)
                .ifPresentOrElse(
                        l -> {
                            double previousRate = l.getSuccessRate() != null ? l.getSuccessRate() : 0;
                            double newRate = DictionaryUtils.computeAverage(rate, previousRate);
                            l.setSuccessRate(newRate);
                            lessonRepository.save(l);
                        },
                        LessonNotFound::new);
    }

    public void deleteLesson(long id) {
        lessonRepository.deleteById(id);
    }

    public void saveLesson(long lessonId, String lessonName, int grade, String userName) {
        Lesson lesson = lessonRepository.findById(lessonId).orElse(new Lesson());

        userRepository.findByUsername(userName).ifPresent(lesson::setUser);
        lesson.setTitle(lessonName.trim());
        lesson.setGrade(grade);

        lessonRepository.save(lesson);
    }
}
