package com.apress.todo.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.apress.todo.interceptor.ToDoMetricInterceptor;

import io.micrometer.core.instrument.MeterRegistry;

@EnableConfigurationProperties(ToDoProperties.class)
@Configuration
public class ToDoConfig {
    @Bean
    public MappedInterceptor metricInterceptor(MeterRegistry registry) {
       return new MappedInterceptor(new String[]{"/**"},
                        new ToDoMetricInterceptor(registry));
    }
}