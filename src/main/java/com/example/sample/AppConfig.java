package com.example.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }
}
