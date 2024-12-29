package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.utils.DictionaryUtils;
import java.util.List;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;

public final class WordService {

    private final WordRepository repository;
    private final int pageSize;

    public WordService(WordRepository repository, int pageSize) {
        this.repository = repository;
        this.pageSize = pageSize;
    }

    public Page<WordDto> findAllLessonIdPaginated(int page, long lessonId) {
        return repository.findAllByLessonIdPaginated(DictionaryUtils.getPageRequest(page, pageSize), lessonId);
    }

    public List<WordDto> findAllByLessonId(long lessonId) {
        return repository.findAllByLessonId(lessonId);
    }

    public void saveWord(Word word) {
        repository.save(word);
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
}
