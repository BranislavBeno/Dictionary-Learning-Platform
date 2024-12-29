package com.dictionary.learning.platform.utils;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

class DictionaryUtilsTest implements WithAssertions {

    @Test
    void testGetPageRequest() {
        PageRequest pageRequest = DictionaryUtils.getPageRequest(1, 10);
        assertThat(pageRequest).isNotNull().satisfies(r -> {
            assertThat(r).isInstanceOf(PageRequest.class);
            assertThat(r.getPageNumber()).isEqualTo(1);
            assertThat(r.getPageSize()).isEqualTo(10);
        });
    }
}
