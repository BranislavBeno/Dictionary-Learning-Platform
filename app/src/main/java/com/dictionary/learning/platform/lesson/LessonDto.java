package com.dictionary.learning.platform.lesson;

public record LessonDto(long id, String title, int grade, Double successRate) {

    public LessonDto {
        if (successRate == null) {
            successRate = 0.0;
        }
    }
}
