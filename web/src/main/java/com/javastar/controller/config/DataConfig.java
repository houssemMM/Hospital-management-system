package com.javastar.controller.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com.javastar.*"})
@EntityScan(basePackages = "com.javastar.*")
@EnableJpaRepositories(basePackages = "com.javastar.*")
public class DataConfig {
}
