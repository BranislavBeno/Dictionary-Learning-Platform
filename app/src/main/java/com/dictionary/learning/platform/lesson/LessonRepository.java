package com.dictionary.learning.platform.lesson;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query(
            """
         SELECT new com.dictionary.learning.platform.lesson.LessonDto(l.id, l.number)
         FROM Lesson l
         WHERE l.id = :lessonId""")
    LessonDto findByLessonId(Long lessonId);

    @Query(
            """
         SELECT new com.dictionary.learning.platform.lesson.LessonDto(l.id, l.number)
         FROM Lesson l
         LEFT JOIN l.user u
         WHERE u.username = :name""")
    Page<LessonDto> findAllByUserNamePaginated(Pageable pageable, String name);
}
