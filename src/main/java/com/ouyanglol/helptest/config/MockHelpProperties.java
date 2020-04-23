package com.ouyanglol.helptest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ouyangduning
 * @date 2020/4/23 14:07
 */
@Data
@ConfigurationProperties(prefix = "mock.help")
public class MockHelpProperties {
    private String packageRoot;
}
