package com.example.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;

@SpringBootApplication
public class SampleApplication {

    private static final Logger log = LoggerFactory.getLogger(SampleApplication.class);

    @Autowired
    MessageSource messageSource;

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);

        log.info("test.intercept");
    }
}
