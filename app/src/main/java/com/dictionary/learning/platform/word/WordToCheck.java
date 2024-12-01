package com.dictionary.learning.platform.word;

public record WordToCheck(String question, String answer) {

    public WordToCheck() {
        this("", "");
    }
}
