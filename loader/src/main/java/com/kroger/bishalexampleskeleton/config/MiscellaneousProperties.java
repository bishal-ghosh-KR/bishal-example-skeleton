package com.kroger.bishalexampleskeleton.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = MiscellaneousConfig.class)
public class MiscellaneousProperties {
    private MiscellaneousConfig miscellaneousConfig;
}
