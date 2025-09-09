package com.klef;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MoodApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MoodApplication.class, args);
        System.out.println("Project running...");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MoodApplication.class);
    }
}
