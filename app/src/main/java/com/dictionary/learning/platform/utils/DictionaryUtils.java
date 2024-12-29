package com.dictionary.learning.platform.utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class DictionaryUtils {

    private DictionaryUtils() {}

    public static PageRequest getPageRequest(int page, int pageSize) {
        return PageRequest.of(page, pageSize);
    }

    public static <T> PageData<T> providePageData(Page<T> page) {
        List<Integer> pageNumbers = providePageNumbers(page.getTotalPages());

        return new PageData<>(page.getContent(), pageNumbers);
    }

    private static List<Integer> providePageNumbers(int totalPages) {
        if (totalPages > 0) {
            return IntStream.rangeClosed(1, totalPages).boxed().toList();
        }

        return Collections.emptyList();
    }

    public record PageData<T>(List<T> content, List<Integer> pageNumbers) {}
}
