package com.dictionary.learning.platform;

import com.dictionary.learning.platform.config.ContainersConfig;
import org.springframework.boot.SpringApplication;

public class DictionaryLearningPlatformTestApplication {

    public static void main(String[] args) {
        SpringApplication.from(DictionaryLearningPlatformApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}
