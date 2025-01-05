package com.dictionary.learning.platform.lesson;

import com.dictionary.learning.platform.user.User;

public record LessonDto(long id, String title, int grade, Double successRate, User user) {

    public LessonDto {
        if (successRate == null) {
            successRate = 0.0;
        }
    }
}
