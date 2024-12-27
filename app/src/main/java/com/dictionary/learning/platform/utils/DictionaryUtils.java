package com.dictionary.learning.platform.utils;

import org.springframework.data.domain.PageRequest;

public class DictionaryUtils {

    private DictionaryUtils() {}

    public static PageRequest getPageRequest(int page, int pageSize) {
        return PageRequest.of(page, pageSize);
    }
}
