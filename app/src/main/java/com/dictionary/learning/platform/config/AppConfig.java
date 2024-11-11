package com.dictionary.learning.platform.config;

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
            @Autowired WordRepository repository, @Value("${word.service.page.size:20}") int pageSize) {
        return new WordService(repository, pageSize);
    }
}
