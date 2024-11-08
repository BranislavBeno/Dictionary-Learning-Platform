package com.dictionary.learning.platform.word;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {

    //   @Query("""
    //         SELECT new com.dictionary.learning.platform.word.WordDto(b.id, b.name, b.author)
    //         FROM Word w
    //         LEFT JOIN w.user u
    //         WHERE u.id = :id""")
    //   Set<WordDto> findAllById(Long id);
}
