package com.kroger.bishalexampleskeleton.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "misc")
public class MiscellaneousConfig {
    private String message;

    public MiscellaneousConfig(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
