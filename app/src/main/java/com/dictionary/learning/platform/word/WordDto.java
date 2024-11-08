package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.user.User;

public record WordDto(Long id, String en, String sk, int lesson, int grade, User user) {}
