package com.ouyanglol.helptest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ouyangduning
 */
@Data
@ConfigurationProperties(prefix = "mock.help")
public class MockHelpProperties {
    private String packageRoot;
}
