package com.dictionary.learning.platform.config;

import com.dictionary.learning.platform.lesson.LessonRepository;
import com.dictionary.learning.platform.lesson.LessonService;
import com.dictionary.learning.platform.user.UserRepository;
import com.dictionary.learning.platform.user.UserService;
import com.dictionary.learning.platform.word.WordRepository;
import com.dictionary.learning.platform.word.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppConfig {

    @Bean
    public WordService wordService(
            @Autowired WordRepository repository, @Value("${dictionary.service.page.size:10}") int pageSize) {
        return new WordService(repository, pageSize);
    }

    @Bean
    public LessonService lessonService(
            @Autowired LessonRepository repository, @Value("${dictionary.service.page.size:10}") int pageSize) {
        return new LessonService(repository, pageSize);
    }

    @Bean
    public UserService userService(@Autowired UserRepository repository) {
        return new UserService(repository);
    }
}
