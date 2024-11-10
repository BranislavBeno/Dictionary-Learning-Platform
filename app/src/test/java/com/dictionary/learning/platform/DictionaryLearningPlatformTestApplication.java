package com.dictionary.learning.platform;

import com.dictionary.learning.platform.config.ContainersConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DictionaryLearningPlatformTestApplication {

    public static void main(String[] args) {
        SpringApplication.from(DictionaryLearningPlatformApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}
