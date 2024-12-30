package com.dictionary.learning.platform.word;

import java.util.concurrent.atomic.AtomicInteger;

public record WordToCheck(String question, String answer, AtomicInteger counter) {

    public WordToCheck(String question, String answer) {
        this(question, answer, new AtomicInteger(0));
    }

    public WordToCheck() {
        this("", "");
    }
}
