package com.ouyanglol.helptest;

import com.ouyanglol.helptest.annotation.EnableMock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@EnableMock
@SpringBootApplication(scanBasePackageClasses = TestMainApplication.class)
public class TestMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestMainApplication.class, args);
    }


}