package com.sirionlabs.docscompare.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:${user.home}/appProperties/document-compare/configuration.properties")
public class ApplicationConfig {
}
