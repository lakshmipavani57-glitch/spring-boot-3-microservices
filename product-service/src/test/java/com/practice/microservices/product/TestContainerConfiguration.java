package com.practice.microservices.product;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

@Configuration
public class TestContainerConfiguration {

    @Bean
    @ServiceConnection
    static MongoDBContainer mongoDbContainer() {
        return new MongoDBContainer("mongo:6.0.5");
    }
}
