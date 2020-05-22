package com.sirionlabs.docscompare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DocsCompareApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DocsCompareApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DocsCompareApplication.class);
    }
}
