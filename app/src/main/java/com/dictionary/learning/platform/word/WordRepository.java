package com.dictionary.learning.platform.word;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WordRepository extends JpaRepository<Word, Long> {

    @Query(
            """
         SELECT new com.dictionary.learning.platform.word.WordDto(w.id, w.en, w.sk, w.successRate, w.lesson)
         FROM Word w
         WHERE w.id = :wordId""")
    WordDto findByWordId(Long wordId);

    @Query(
            """
         SELECT new com.dictionary.learning.platform.word.WordDto(w.id, w.en, w.sk, w.successRate, w.lesson)
         FROM Word w
         LEFT JOIN w.lesson l
         WHERE l.id = :lessonId""")
    Page<WordDto> findAllByLessonIdPaginated(Pageable pageable, long lessonId);

    @Query(
            """
         SELECT new com.dictionary.learning.platform.word.WordDto(w.id, w.en, w.sk, w.successRate, w.lesson)
         FROM Word w
         LEFT JOIN w.lesson l
         WHERE l.id = :lessonId""")
    List<WordDto> findAllByLessonId(long lessonId);
}
