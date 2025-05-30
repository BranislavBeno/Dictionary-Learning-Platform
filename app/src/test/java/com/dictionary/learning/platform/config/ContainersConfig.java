package com.dictionary.learning.platform.config;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class ContainersConfig {

    @Bean
    @ServiceConnection
    @RestartScope
    PostgreSQLContainer<?> postgresSqlContainer() {
        return new PostgreSQLContainer<>("postgres:17.5");
    }
}
