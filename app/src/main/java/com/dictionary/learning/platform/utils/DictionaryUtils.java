package com.dictionary.learning.platform.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class DictionaryUtils {

    public static final int SCALE = 3;
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private DictionaryUtils() {}

    public static double computeRate(int size, int sum) {
        return BigDecimal.valueOf(size)
                .divide(BigDecimal.valueOf(sum), SCALE, ROUNDING_MODE)
                .doubleValue();
    }

    public static double computeAverage(double newRate, double previousRate) {
        double rate = previousRate == 0 ? newRate : (previousRate + newRate * 2) / 3;
        return BigDecimal.valueOf(rate).setScale(SCALE, ROUNDING_MODE).doubleValue();
    }

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
