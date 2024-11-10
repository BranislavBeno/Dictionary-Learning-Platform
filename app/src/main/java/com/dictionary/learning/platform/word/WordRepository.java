package com.dictionary.learning.platform.word;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WordRepository extends JpaRepository<Word, Long> {

    @Query(
            """
             SELECT w
             FROM Word w
             LEFT JOIN w.user u
             WHERE u.id = :id""")
    Set<Word> findAllByUserId(long id);
}
