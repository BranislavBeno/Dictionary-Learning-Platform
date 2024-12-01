package com.dictionary.learning.platform.word;

import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WordRepository extends JpaRepository<Word, Long> {

    @Query(
            """
         SELECT new com.dictionary.learning.platform.word.WordDto(w.id, w.en, w.sk, w.lesson, w.grade)
         FROM Word w
         LEFT JOIN w.user u
         WHERE u.id = :id""")
    Page<WordDto> findAllByUserId(Pageable pageable, long id);

    @Query(
            """
         SELECT new com.dictionary.learning.platform.word.WordDto(w.id, w.en, w.sk, w.lesson, w.grade)
         FROM Word w
         LEFT JOIN w.user u
         WHERE u.id = :id AND w.grade = :grade""")
    Set<WordDto> findAllByUserIdByGrade(long id, int grade);

    @Query(
            """
         SELECT new com.dictionary.learning.platform.word.WordDto(w.id, w.en, w.sk, w.lesson, w.grade)
         FROM Word w
         LEFT JOIN w.user u
         WHERE u.username = :name AND w.grade = :grade AND w.lesson = :lesson""")
    Set<WordDto> findAllByUserNameByGradeByLesson(String name, int grade, int lesson);

    @Query(
            """
         SELECT new com.dictionary.learning.platform.word.WordDto(w.id, w.en, w.sk, w.lesson, w.grade)
         FROM Word w
         LEFT JOIN w.user u
         WHERE u.id = :id AND w.grade = :grade AND w.lesson BETWEEN :firstLesson AND :lastLesson""")
    Set<WordDto> findAllByUserIdByGradeBetweenLessons(long id, int grade, int firstLesson, int lastLesson);
}
