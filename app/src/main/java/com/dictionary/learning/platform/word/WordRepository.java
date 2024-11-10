package com.dictionary.learning.platform.word;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WordRepository extends JpaRepository<Word, Long> {

    @Query(
            """
             SELECT new com.dictionary.learning.platform.word.WordDto(w.id, w.en, w.sk, w.lesson, w.grade)
             FROM Word w
             LEFT JOIN w.user u
             WHERE u.id = :id""")
    Set<WordDto> findAllByUserId(long id);

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
             WHERE u.id = :id AND w.grade = :grade AND w.lesson = :lesson""")
    Set<WordDto> findAllByUserIdByGradeByLesson(long id, int grade, int lesson);
}
