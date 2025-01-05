package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.lesson.Lesson;

public record WordDto(Long id, String en, String sk, Double successRate, Lesson lesson) {}
